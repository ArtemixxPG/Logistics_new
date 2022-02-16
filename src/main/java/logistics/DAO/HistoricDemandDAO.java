package logistics.DAO;

import logistics.entityes.HistoricDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricDemandDAO extends JpaRepository<HistoricDemand, Long> {


}
