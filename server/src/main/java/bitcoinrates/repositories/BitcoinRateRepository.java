package bitcoinrates.repositories;

import bitcoinrates.entities.BitcoinRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BitcoinRateRepository extends CrudRepository<BitcoinRate, Long> {

    List<BitcoinRate> findByCodeAndTimestampBetween(String code, Date from, Date to);
    List<BitcoinRate> findByTimestamp(Date timestamp);
}