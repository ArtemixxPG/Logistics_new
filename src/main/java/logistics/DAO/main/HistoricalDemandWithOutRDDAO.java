package logistics.DAO.main;

import logistics.entityes.HistoricalDemandWithoutRD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricalDemandWithOutRDDAO extends JpaRepository<HistoricalDemandWithoutRD, Long> {
}
