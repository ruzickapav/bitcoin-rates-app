package bitcoinrates.repositories;

import bitcoinrates.entities.BitcoinRatesSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BitcoinRatesSnapshotRepository extends CrudRepository<BitcoinRatesSnapshot, Long> {
    Optional<BitcoinRatesSnapshot> findByTimestamp(Date timestamp);

}
