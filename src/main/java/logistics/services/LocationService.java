package logistics.services;

import logistics.entityes.Customers;
import logistics.entityes.DCsAndFactories;
import logistics.entityes.Locations;
import logistics.entityes.Suppliers;

import java.util.List;

public interface LocationService {
    Long save(Locations locations);
    void delete(Locations locations);
    Long updateSuppliers(Locations locations, Suppliers suppliers);
    Long updateCustomers(Locations locations, Customers customers);
    Long updateDC_F(Locations locations, DCsAndFactories dCsAndFactories);
    List<Locations> getAll();
}
