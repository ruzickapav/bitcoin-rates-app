package bitcoinrates.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class BitcoinRatesSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    Date timestamp;
    String disclaimer;
    String chartName;
    @OneToMany(cascade = CascadeType.ALL)
    List<BitcoinRate> bitcoinRates;

}
