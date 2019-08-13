package bitcoinrates.services;

import bitcoinrates.entities.BitcoinRate;
import bitcoinrates.repositories.BitcoinRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BitcoinRatesService {

    private final BitcoinRateRepository bitcoinRateRepository;

    @Autowired
    public BitcoinRatesService(BitcoinRateRepository bitcoinRateRepository) {
        this.bitcoinRateRepository = bitcoinRateRepository;
    }

    @Transactional
    public void save(LocalDateTime timestamp, List<BitcoinRate> rates) {
        if (bitcoinRateRepository.findByTimestamp(timestamp).isEmpty()) {
            rates.stream().forEach(rate -> bitcoinRateRepository.save(rate));
        }
    }

    public List<BitcoinRate> getBitcoinRates(String code, LocalDateTime from, LocalDateTime to) {
        return bitcoinRateRepository
                .findByCodeAndTimestampBetween(code, from, to);
    }
}
