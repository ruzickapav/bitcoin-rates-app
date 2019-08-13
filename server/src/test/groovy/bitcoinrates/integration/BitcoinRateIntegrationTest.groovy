package bitcoinrates.integration

import bitcoinrates.entities.BitcoinRate
import bitcoinrates.repositories.BitcoinRateRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime

import static java.time.temporal.ChronoUnit.HOURS
import static java.time.temporal.ChronoUnit.MINUTES
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BitcoinRatesControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    BitcoinRateRepository bitcoinRateRepository

    def 'Get bitcoin rate for currency code'() {
        given: "Prepare database"
        def acceptedTimes = LocalDateTime.now().minus(5, MINUTES)
        def refusedTimes = LocalDateTime.now().minus(5, HOURS)
        def db_rates = [
                new BitcoinRate(timestamp: refusedTimes, code: 'USD', symbol: '7373', description: 'xx', rate_float: 12.1, rate: '12.1'),
                new BitcoinRate(timestamp: acceptedTimes, code: 'EUR', symbol: '7373', description: 'xx', rate_float: 12.1, rate: '12.1'),
                new BitcoinRate(timestamp: acceptedTimes, code: 'USD', symbol: '7373', description: 'xx', rate_float: 10.1, rate: '10.1'),
                new BitcoinRate(timestamp: acceptedTimes, code: 'JPA', symbol: '7373', description: 'xx', rate_float: 13.1, rate: '9.1'),
        ]
        db_rates.forEach({ rate -> bitcoinRateRepository.save(rate) })

        when:

        def dateFrom = LocalDateTime.now().minus(1, HOURS)
        def dateTo = LocalDateTime.now()

        def result = mvc.perform(get('/rates/USD/')
                .accept(MediaType.APPLICATION_JSON)
                .param("fromDate", dateFrom.toString())
                .param("toDate", dateTo.toString())
        )
                .andExpect(status().isOk())
                .andReturn()

        then: 'Device is received in response enriched by id'
        def rates = new JsonSlurper().parseText(result.response.getContentAsString())
        rates.size() == 1
        rates.get(0).rate_float == 10.1
    }
}