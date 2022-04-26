package logistics.DAO.main;

import logistics.entityes.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersDAO extends JpaRepository<Suppliers, Long> {
    @Query("select sp from TimePeriod as sp where sp.name=:name")
    Suppliers getSuppliersByName(@Param("name") String name);
}
