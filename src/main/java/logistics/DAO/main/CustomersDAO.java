package logistics.DAO.main;

import logistics.entityes.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersDAO extends JpaRepository<Customers, Long> {
    @Query("select c from Customers as c where c.name=:name")
    Customers getCustomersByName(@Param("name") String name);
}
