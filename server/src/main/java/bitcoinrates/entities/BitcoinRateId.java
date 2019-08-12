package bitcoinrates.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BitcoinRateId implements Serializable {
    Date timestamp;
    String code;

    public BitcoinRateId(Date timestamp, String code) {
        this.timestamp = timestamp;
        this.code = code;
    }

    public BitcoinRateId() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitcoinRateId that = (BitcoinRateId) o;
        return timestamp.equals(that.timestamp) &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, code);
    }
}
