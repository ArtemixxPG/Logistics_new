package logistics.services;


import logistics.DAO.main.LocationsDAO;
import logistics.DAO.main.SuppliersDAO;
import logistics.entityes.Suppliers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SuppliersServiceImpl implements SuppliersService{
    private SuppliersDAO suppliersDAO;
    private LocationsDAO locationsDAO;

    public SuppliersServiceImpl(SuppliersDAO suppliersDAO, LocationsDAO locationsDAO){
        this.suppliersDAO = suppliersDAO;
        this.locationsDAO = locationsDAO;
    }


    @Override
    public Long save(Suppliers suppliers) {
        suppliers.setLocation(locationsDAO.getLocationsByName(suppliers.getName().split("_")[1]));
    //    locationsDAO.updateSuppliers(suppliers, suppliers.getName());
        return suppliersDAO.save(suppliers).getId();
    }

    @Override
    public Long update(Suppliers suppliers) {
       Suppliers newSuppliers = suppliersDAO.getById(suppliers.getId());
        newSuppliers.setIcon(suppliers.getIcon());
        newSuppliers.setInclusion_type(suppliers.getInclusion_type());
        newSuppliers.setName(suppliers.getName());
        newSuppliers.setLocation(suppliers.getLocation());
        newSuppliers.setType(suppliers.getType());
        return suppliersDAO.save(newSuppliers).getId();
    }

    @Override
    public void delete(Suppliers suppliers) {
        suppliersDAO.delete(suppliers);
    }

    @Override
    public List<Suppliers> getAll() {
        return suppliersDAO.findAll();
    }
}
