package logistics.DAO.main;

import logistics.entityes.BOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BOMDAO extends JpaRepository<BOM, Long> {
}
