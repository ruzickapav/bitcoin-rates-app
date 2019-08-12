package bitcoinrates.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BitcoinRatesSnapshotDTO {
    TimeDTO timeDTO;
    String disclaimer;
    String chartName;
    List<BitcoinRateDTO> rates;
}
