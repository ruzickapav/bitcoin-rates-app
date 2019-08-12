package bitcoinrates.dtos;

import lombok.Data;

@Data
public class BitcoinRateDTO {
    String code;
    String symbol;
    String rate;
    String description;
    Double rateFloat;
}
