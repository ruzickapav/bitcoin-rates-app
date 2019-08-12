package bitcoinrates.repositories;

import bitcoinrates.entities.BitcoinRatesSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BitcoinRatesSnapshotRepository extends CrudRepository<BitcoinRatesSnapshot, Long> {

    List<BitcoinRatesSnapshot> findBySerialAndTimestampBetweenOrderByTimestamp(String serial, Date from, Date to);

}
