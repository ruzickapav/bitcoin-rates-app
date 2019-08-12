package bitcoinrates.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class BitcoinRate {
    @Id
    String code;
    String symbol;
    String description;
    Double rate;
    String rateText;

    @Id
    @ManyToOne
    BitcoinRatesSnapshot bitcoinRatesSnapshot;
}
