package logistics.DAO.main;

import logistics.entityes.Customers;
import logistics.entityes.HistoricalDemand;
import logistics.entityes.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricalDemandDAO extends JpaRepository<HistoricalDemand, Long> {


    @Query("select distinct hd.customers from HistoricalDemand as hd ")
    List<Customers> getAllCurrentCustomers();

    @Query("select  hd from HistoricalDemand as hd where hd.customers =:customer and" +
                        " hd.products =:products and hd.price =:price and hd.count =:count")
    List<HistoricalDemand> getByCustomersAndProducts(@Param("customer")Customers customers,
                                        @Param("products")Products products,
                                               @Param("price") Long price, @Param("count")Integer count);

    @Query("select sum(hd.count) from HistoricalDemand as hd where hd.customers =:customer and" +
            " hd.products =:products")
    Integer sumCountProductsForAllPeriod(@Param("customer")Customers customers,
                                         @Param("products")Products products);
}
