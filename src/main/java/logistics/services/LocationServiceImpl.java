package logistics.services;

import logistics.DAO.LocationsDAO;
import logistics.entityes.Customers;
import logistics.entityes.DCsAndFactories;
import logistics.entityes.Locations;
import logistics.entityes.Suppliers;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService{

    private LocationsDAO locationsDAO;

    public LocationServiceImpl(LocationsDAO locationsDAO){
        this.locationsDAO = locationsDAO;
    }

    @Override
    public Long save(Locations locations) {
        if(locationsDAO.getLocationsByName(locations.getName())== null) {

            return locationsDAO.save(locations).getId();
        }
        return null;
    }

    @Override
    public void delete(Locations locations) {

    }

    @Override
    public Long updateSuppliers(Locations locations, Suppliers suppliers) {
        Locations newLocation = locationsDAO.getLocationsByName(locations.getName());
        newLocation.setSuppliers(suppliers);

        return locationsDAO.save(newLocation).getId();
    }

    @Override
    public Long updateCustomers(Locations locations, Customers customers) {
        Locations newLocation = locationsDAO.getLocationsByName(locations.getName());
        newLocation.setCustomers(customers);

        return locationsDAO.save(newLocation).getId();
    }

    @Override
    public Long updateDC_F(Locations locations, DCsAndFactories dCsAndFactories) {
        Locations newLocation = locationsDAO.getLocationsByName(locations.getName());
        newLocation.setdCsAndFactories(dCsAndFactories);

        return locationsDAO.save(newLocation).getId();
    }

    @Override
    public List<Locations> getAll() {
        return locationsDAO.findAll();
    }
}
