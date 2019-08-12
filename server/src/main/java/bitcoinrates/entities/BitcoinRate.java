package bitcoinrates.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BitcoinRate implements Serializable {
    @Id
    String code;

    String symbol;
    String description;
    Double rate_float;
    String rate;

    @Id
    @ManyToOne()
    BitcoinRatesSnapshot bitcoinRatesSnapshot;

    public Double getRate_float() {
        return rate_float;
    }

    public void setRate_float(Double rate_float) {
        this.rate_float = rate_float;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BitcoinRatesSnapshot getBitcoinRatesSnapshot() {
        return bitcoinRatesSnapshot;
    }

    public void setBitcoinRatesSnapshot(BitcoinRatesSnapshot bitcoinRatesSnapshot) {
        this.bitcoinRatesSnapshot = bitcoinRatesSnapshot;
    }
}
