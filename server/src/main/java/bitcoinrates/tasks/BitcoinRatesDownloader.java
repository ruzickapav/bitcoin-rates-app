package bitcoinrates.tasks;

import bitcoinrates.dtos.BitcoinRatesSnapshotDTO;
import bitcoinrates.entities.BitcoinRate;
import bitcoinrates.services.BitcoinRatesService;
import bitcoinrates.utils.JavaScriptMessageConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BitcoinRatesDownloader {

    private final URI CURRENT_PRICE_URI = URI.create("https://api.coindesk.com/v1/bpi/currentprice.json");

    private final RestTemplate restTemplate;

    private final BitcoinRatesService bitcoinRatesService;

    private static final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public BitcoinRatesDownloader(RestTemplate restTemplate, BitcoinRatesService bitcoinRatesService) {
        this.restTemplate = restTemplate;
        this.bitcoinRatesService = bitcoinRatesService;
        this.restTemplate.getMessageConverters().add(new JavaScriptMessageConverter());
    }

    private List<BitcoinRate> translate(BitcoinRatesSnapshotDTO bitcoinRatesSnapshotDTO) {

        var rates = bitcoinRatesSnapshotDTO
                .getBpi()
                .entrySet()
                .stream()
                .map(entry -> modelMapper.map(entry.getValue(), BitcoinRate.class))
                .collect(Collectors.toList());

        Date timestamp = bitcoinRatesSnapshotDTO.getTime().getUpdatedISO();
        rates.stream().forEach(rate -> rate.setTimestamp(timestamp));

        return rates;
    }

    @Scheduled(fixedRate = 12000)
    void getCurrentBitcoinRates() {

        var response =
                restTemplate.exchange(CURRENT_PRICE_URI, HttpMethod.GET, null, BitcoinRatesSnapshotDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            var bitcoinRatesSnapshotDTO = response.getBody();
            var bitcoinRates = translate(bitcoinRatesSnapshotDTO);
            var timestamp = bitcoinRatesSnapshotDTO.getTime().getUpdatedISO();

            bitcoinRatesService.save(timestamp, bitcoinRates);
        }
    }
}
