package logistics.services;

import logistics.DAO.main.DC_FactoriesDAO;
import logistics.DAO.main.LocationsDAO;
import logistics.entityes.DCsAndFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DcsAndFactoriesServiceImpl implements DCsAndFactoriesService {
   private DC_FactoriesDAO dc_factoriesDAO;
   private LocationsDAO locationsDAO;


    public DcsAndFactoriesServiceImpl(DC_FactoriesDAO dc_factoriesDAO, LocationsDAO locationsDAO) {
        this.dc_factoriesDAO = dc_factoriesDAO;
        this.locationsDAO = locationsDAO;

    }


    @Override
    public Long save(DCsAndFactories dCsAndFactories) {
        dCsAndFactories.setLocation(locationsDAO.getLocationsByName(dCsAndFactories.getName().split("_")[1]));
       // locationsDAO.updateDCsAndFactories(dCsAndFactories, dCsAndFactories.getName());
        return dc_factoriesDAO.save(dCsAndFactories).getId();
    }

    @Override
    public Long update(DCsAndFactories dCsAndFactories) {
        DCsAndFactories dCsAndFactories1 = dc_factoriesDAO.getById(dCsAndFactories.getId());
        dCsAndFactories1.setAggOrByLoc(dCsAndFactories.getAggOrByLoc());
        dCsAndFactories1.setCapacity(dCsAndFactories.getCapacity());
        dCsAndFactories1.setCapacity_unit(dCsAndFactories.getCapacity_unit());
        dCsAndFactories1.setInitially_open(dCsAndFactories.getInitially_open());
        dCsAndFactories1.setName(dCsAndFactories.getName());
        dCsAndFactories1.setLocation(dCsAndFactories.getLocation());
        dCsAndFactories1.setType(dCsAndFactories.getType());
        return dc_factoriesDAO.save(dCsAndFactories1).getId();
    }

    @Override
    public void delete(DCsAndFactories dCsAndFactories) {
        dc_factoriesDAO.delete(dCsAndFactories);
    }

    @Override
    public List<DCsAndFactories> getAll() {
        return dc_factoriesDAO.findAll();
    }
}
