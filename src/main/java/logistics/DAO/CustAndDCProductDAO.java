package logistics.DAO;

import logistics.entityes.CustAndDCProduct;
import logistics.entityes.Customers;
import logistics.entityes.DCsAndFactories;
import logistics.entityes.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustAndDCProductDAO extends JpaRepository<CustAndDCProduct,Long> {

    @Query("select cadcp from CustAndDCProduct as cadcp where cadcp.products =:products and  cadcp.dCsAndFactories =:dcaf " +
            "and cadcp.customers =:cust")
    CustAndDCProduct getCustAndDCProduct(@Param("products")Products products, @Param("dcaf")DCsAndFactories dCsAndFactories,
                                         @Param("cust")Customers customers);
}
