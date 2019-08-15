package bitcoinrates.resources;

import bitcoinrates.dtos.BitcoinRateTickDTO;
import bitcoinrates.entities.BitcoinRate;
import bitcoinrates.services.BitcoinRatesService;
import bitcoinrates.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class BitcoinRatesController {

    @Autowired
    private BitcoinRatesService bitcoinRatesService;

    @CrossOrigin
    @GetMapping("/rates/{code}/")
    public List<BitcoinRateTickDTO> getRateHistory(
            @PathVariable(value = "code") String code,
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        List<BitcoinRate> bitcoinRates = bitcoinRatesService
                .getBitcoinRates(code, fromDate, toDate);
        return bitcoinRates.stream()
                .map(rate -> new BitcoinRateTickDTO(DateTimeUtils.toUTC(rate.getTimestamp()), rate.getRate_float()))
                .collect(Collectors.toList());
    }
}
