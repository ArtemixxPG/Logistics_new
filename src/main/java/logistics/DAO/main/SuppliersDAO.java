package logistics.DAO.main;

import logistics.entityes.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersDAO extends JpaRepository<Suppliers, Long> {
}
