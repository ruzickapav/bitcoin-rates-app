package bitcoinrates.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class BitcoinRatesSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<BitcoinRate> getBitcoinRates() {
        return bitcoinRates;
    }

    public void setBitcoinRates(List<BitcoinRate> bitcoinRates) {
        this.bitcoinRates = bitcoinRates;
    }

    @Column(unique = true)
    Date timestamp;
    @OneToMany(cascade = {CascadeType.ALL})
    List<BitcoinRate> bitcoinRates;
}
