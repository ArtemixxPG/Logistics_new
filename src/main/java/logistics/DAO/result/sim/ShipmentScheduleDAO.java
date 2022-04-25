package logistics.DAO.result.sim;

import logistics.entityes.results.sim.ShipmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentScheduleDAO extends JpaRepository<ShipmentSchedule, Long> {
}
