package logistics.DAO.main;

import logistics.entityes.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDAO extends JpaRepository<Sale, Long> {

    @Query("select s from Sale as s where s.products.name=:nameProduct and s.customers=:custom")
    public Sale getSaleByProductsANDCustomers(@Param("nameProduct")String nameProduct, @Param("custom")String customers);

    //TODO delete from sale where product is null

}
