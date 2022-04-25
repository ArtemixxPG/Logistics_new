package logistics.DAO.main;

import logistics.entityes.DemandForAllPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandForAllPeriodDAO extends JpaRepository<DemandForAllPeriod, Long> {
}
