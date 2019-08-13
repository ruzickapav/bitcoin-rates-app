package bitcoinrates.repositories;

import bitcoinrates.entities.BitcoinRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitcoinRateRepository extends CrudRepository<BitcoinRate, Long> {

    List<BitcoinRate> findByCodeAndTimestampBetween(String code, LocalDateTime from, LocalDateTime to);

    List<BitcoinRate> findByTimestamp(LocalDateTime timestamp);
}