package bitcoinrates.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinRateTickDTO {
    LocalDateTime timestamp;
    Double rate_float;

    public BitcoinRateTickDTO() {
    }

    public BitcoinRateTickDTO(LocalDateTime timestamp, Double rate_float) {
        this.timestamp = timestamp;
        this.rate_float = rate_float;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getRate_float() {
        return rate_float;
    }

    public void setRate_float(Double rate_float) {
        this.rate_float = rate_float;
    }
}
