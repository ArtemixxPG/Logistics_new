package logistics.DAO.main;

import logistics.entityes.DCsAndFactories;
import logistics.entityes.HistoricalProduction;
import logistics.entityes.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricalProductionDAO extends JpaRepository<HistoricalProduction, Long> {

    @Query("select hp.products from HistoricalProduction as hp where hp.dCsAndFactories =:dCAFName")
    List<Products> getProductByDCName(@Param("dCAFName") DCsAndFactories dCAFName);
}
