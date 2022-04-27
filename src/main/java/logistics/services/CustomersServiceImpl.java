package logistics.services;

import logistics.DAO.main.CustomersDAO;
import logistics.DAO.main.LocationsDAO;
import logistics.entityes.Customers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService{


    private CustomersDAO customersDao;
    private LocationsDAO locationsDAO;


    public CustomersServiceImpl(CustomersDAO customersDao, LocationsDAO locationsDAO){
        this.customersDao = customersDao;
        this.locationsDAO = locationsDAO;

    }


    @Override
    public Long save(Customers customers) {
        customers.setLocations(locationsDAO.getLocationsByName(customers.getName().split("_")[1]));
     //   locationsDAO.updateCustomers(customers, customers.getName());
        if (customersDao.getCustomersByName(customers.getName()) == null) {
            return customersDao.save(customers).getId();
        }
        return null;
    }

    @Override
    public Long update(Customers customers) {
        Customers newCustomers = customersDao.getById(customers.getId());
        newCustomers.setIcon(customers.getIcon());
        newCustomers.setInclusion(customers.getInclusion());
        newCustomers.setName(customers.getName());
        newCustomers.setLocations(customers.getLocations());
        newCustomers.setType(customers.getType());
        return customersDao.save(customers).getId();
    }

    @Override
    public void delete(Customers customers) {
        customersDao.delete(customers);
    }

    @Override
    public List<Customers> getAll() {
        return customersDao.findAll();
    }
}
