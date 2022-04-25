package logistics.DAO.main;

import logistics.entityes.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Products, Long> {

    @Query("select p from Products as p where p.name=:name")
    Products getProductsByName(@Param("name")String name);
}
