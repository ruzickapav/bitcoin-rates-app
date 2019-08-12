package bitcoinrates.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinRateTickDTO {
    Date timestamp;
    Double rate_float;

    public BitcoinRateTickDTO() {
    }

    public BitcoinRateTickDTO(Date timestamp, Double rate_float) {
        this.timestamp = timestamp;
        this.rate_float = rate_float;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getRate_float() {
        return rate_float;
    }

    public void setRate_float(Double rate_float) {
        this.rate_float = rate_float;
    }
}
