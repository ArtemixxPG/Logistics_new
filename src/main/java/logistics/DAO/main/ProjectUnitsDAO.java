package logistics.DAO.main;

import logistics.entityes.ProjectUnits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUnitsDAO extends JpaRepository<ProjectUnits, Long> {
}
