package bitcoinrates.services;

import bitcoinrates.entities.BitcoinRatesSnapshot;
import bitcoinrates.repositories.BitcoinRatesSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinRatesService {

    private final BitcoinRatesSnapshotRepository bitcoinRatesSnapshotRepository;

    @Autowired
    public BitcoinRatesService(BitcoinRatesSnapshotRepository bitcoinRatesSnapshotRepository) {
        this.bitcoinRatesSnapshotRepository = bitcoinRatesSnapshotRepository;
    }

    public void save(BitcoinRatesSnapshot bitcoinRatesSnapshot) {
        if (bitcoinRatesSnapshotRepository.findByTimestamp(bitcoinRatesSnapshot.getTimestamp()).isEmpty()) {
            bitcoinRatesSnapshot.getBitcoinRates()
                    .stream()
                    .forEach(bitcoinRate -> bitcoinRate.setBitcoinRatesSnapshot(bitcoinRatesSnapshot));
            bitcoinRatesSnapshotRepository.save(bitcoinRatesSnapshot);
        }
    }
}
