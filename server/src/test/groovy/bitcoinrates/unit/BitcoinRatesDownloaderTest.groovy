package bitcoinrates.unit

import bitcoinrates.dtos.BitcoinRateDTO
import bitcoinrates.dtos.BitcoinRatesSnapshotDTO
import bitcoinrates.dtos.TimeDTO
import bitcoinrates.entities.BitcoinRate
import bitcoinrates.services.BitcoinRatesService
import bitcoinrates.tasks.BitcoinRatesDownloader
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.client.RestTemplate
import spock.lang.Specification


class BitcoinRatesDownloaderTest extends Specification {
    
    void 'Get current rate from rest and persist it'() {
        given: 'Rest template gives BitcoinRateSnapshotDTO'
        def date = new Date()

        def rateSnaphotDTO = new BitcoinRatesSnapshotDTO()
        rateSnaphotDTO.setBpi(new HashMap<String, BitcoinRateDTO>())
        rateSnaphotDTO.getBpi().
                put("USD", new BitcoinRateDTO(code: 'USD', symbol: '7373', description: 'xx', rate_float: 12.1, rate: '12.1'))
        rateSnaphotDTO.setTime(new TimeDTO(updatedISO: date))

        def restTemplateMock = Mock(RestTemplate.class)
        def uri = URI.create("https://api.coindesk.com/v1/bpi/currentprice.json")
        restTemplateMock.getMessageConverters() >> new ArrayList<HttpMessageConverter<?>>()

        restTemplateMock.exchange(
                uri,
                HttpMethod.GET,
                null,
                BitcoinRatesSnapshotDTO.class) >> new ResponseEntity<BitcoinRatesSnapshotDTO>(rateSnaphotDTO, HttpStatus.OK)

        and: 'BitcoinRatesService mock where data are persist'
        def bitcoinRatesService = Mock(BitcoinRatesService.class)

        when: "downloader retrieves bitcoin rates from server"
        def downloader = new BitcoinRatesDownloader(restTemplateMock, bitcoinRatesService)
        downloader.getCurrentBitcoinRates()

        then: "rates are saved using bitcoinRatesService"
        1 * bitcoinRatesService.save(_, _) >> {
            arguments ->
                List<BitcoinRate> data = arguments[1]
                assert data.size() == 1
                assert data.get(0).timestamp == arguments[0]
                assert data.get(0).rate_float == 12.1
                assert data.get(0).code == 'USD'
        }

    }

}
