package bitcoinrates.resources;

import bitcoinrates.dtos.BitcoinRateTickDTO;
import bitcoinrates.entities.BitcoinRate;
import bitcoinrates.services.BitcoinRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class BitcoinRatesController {

    @Autowired
    private BitcoinRatesService bitcoinRatesService;

    private Date toDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    @CrossOrigin
    @GetMapping("/rates/{code}/")
    public List<BitcoinRateTickDTO> getRateHistory(@PathVariable(value = "code") String code,
                                                   @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                   @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        List<BitcoinRate> bitcoinRates = bitcoinRatesService
                .getBitcoinRates(code, toDate(fromDate), toDate(toDate));
        return bitcoinRates.stream()
                .map(rate -> new BitcoinRateTickDTO(rate.getTimestamp(), rate.getRate_float()))
                .collect(Collectors.toList());
    }
}
