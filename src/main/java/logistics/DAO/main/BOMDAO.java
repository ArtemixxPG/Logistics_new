package logistics.DAO.main;

import logistics.entityes.BOM;
import logistics.entityes.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BOMDAO extends JpaRepository<BOM, Long> {
    @Query("select b from Customers as b where c.name=:name")
    BOM getBOMByName(@Param("name") String name);
}
