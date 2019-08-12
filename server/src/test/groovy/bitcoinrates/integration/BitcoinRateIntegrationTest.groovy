package bitcoinrates.integration

import bitcoinrates.entities.BitcoinRate

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import webapp.entities.Market

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    BitcoinRatesSnapshotRepository bitcoinRatesSnapshotRepository

    def 'Get bitcoin rate for currency code'() {
        given: "Prepare database"
        def snapshot = new BitcoinRatesSnapshot(timestamp: new Date()
                , bitcoinRates: [new BitcoinRate(code: "EUR", symbol: "7373", description: "xx", rate_float: 12.1, rate: "12.1")])
        snapshot.bitcoinRates.forEach({ rate -> rate.bitcoinRatesSnapshot = snapshot })
        bitcoinRatesSnapshotRepository.save(snapshot)

        def market = marketRepository.save(new Market(name: "m1", serial: "sm1"))
        when:
        def serial = 'ax1'
        def deviceJson = "{\"serial\": \"${serial}\", \"name\":\"aba\", \"marketId\":1}"

        def result = mvc.perform(post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deviceJson))
                .andExpect(status().isOk())
                .andReturn()
        then: 'Device is stored in database'
        def storedDevice = deviceRepository.findBySerial(serial)
        storedDevice.empty == false

        then: 'Device is received in response enriched by id'
        def objectDevice = new JsonSlurper().parseText(result.response.getContentAsString())
        objectDevice.serial == serial
        objectDevice.id == storedDevice.get().id
    }
}