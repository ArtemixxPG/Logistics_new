package logistics.DAO;

import logistics.entityes.ScenarioSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioSettingDAO extends JpaRepository<ScenarioSettings, Long> {
}
