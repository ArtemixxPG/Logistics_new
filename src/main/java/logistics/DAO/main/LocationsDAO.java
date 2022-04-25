package logistics.DAO.main;

import logistics.entityes.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsDAO extends JpaRepository<Locations, Long> {

    @Query("select l from Locations as l where l.name = :name")
    Locations getLocationsByName(@Param("name")String name);

//    @Query("update Locations as l set l.customers=:customers where l.name =:name")
//    Long updateCustomers(@Param("customers") Customers customers, @Param("name")String name);
//
//    @Query("update Locations as l set l.suppliers=:suppliers where l.name =:name")
//    Long updateSuppliers(@Param("suppliers")Suppliers suppliers, @Param("name")String name);
//
//    @Query("update Locations as l set l.dCsAndFactories=:dCsAndFactories where l.name =:name")
//    Long updateDCsAndFactories(@Param("dCsAndFactories")DCsAndFactories dCsAndFactories, @Param("name")String name);
}
