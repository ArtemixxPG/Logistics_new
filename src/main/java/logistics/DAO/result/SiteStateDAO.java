package logistics.DAO.result;

import logistics.entityes.SiteState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteStateDAO extends JpaRepository<SiteState, Long> {


}
