package logistics.DAO.main;

import logistics.entityes.Icon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconDAO extends JpaRepository<Icon, Long> {
}
