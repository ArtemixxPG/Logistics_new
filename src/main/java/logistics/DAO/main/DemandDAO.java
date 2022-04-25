package logistics.DAO.main;

import logistics.entityes.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DemandDAO extends JpaRepository<Demand, Long> {

    @Query("select d from Demand as d where d.customers.name =:nameCust and d.products.name=:nameProduct"
            )
    List<Demand> getDemandByCustomersAndAndProducts(@Param("nameCust")String nameCust,
                                                    @Param("nameProduct")String nameProduct
                                                   );
}
