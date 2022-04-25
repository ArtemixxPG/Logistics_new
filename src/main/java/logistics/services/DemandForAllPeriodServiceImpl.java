package logistics.services;

import logistics.DAO.main.DemandForAllPeriodDAO;
import logistics.DAO.main.HistoricalDemandDAO;
import logistics.DAO.main.ProductDAO;
import logistics.DAO.main.TimePeriodDAO;
import logistics.entityes.Customers;
import logistics.entityes.DemandForAllPeriod;
import logistics.entityes.Products;
import logistics.entityes.TimePeriod;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DemandForAllPeriodServiceImpl implements DemandForAllPeriodService{

    private DemandForAllPeriodDAO demandForAllPeriodDAO;
    private HistoricalDemandDAO historicalDemandDAO;
    private TimePeriodDAO timePeriodDAO;
    private ProductDAO productDAO;



    public DemandForAllPeriodServiceImpl(DemandForAllPeriodDAO demandForAllPeriodDAO,
                                         HistoricalDemandDAO historicalDemandDAO, TimePeriodDAO timePeriodDAO,
                                         ProductDAO productDAO){
        this.demandForAllPeriodDAO = demandForAllPeriodDAO;
        this.historicalDemandDAO = historicalDemandDAO;
        this.timePeriodDAO = timePeriodDAO;
        this.productDAO = productDAO;
    }




    @Override
    public void save() {
        List<Customers> customers = getCurrentCustomers();
        for(int i = customers.size() - 20; i < customers.size(); i++) {
            for (TimePeriod timePeriod : timePeriodDAO.findAll()) {
                for (Products products : productDAO.findAll()) {
                    DemandForAllPeriod demand = new DemandForAllPeriod();
                    demand.setCount(historicalDemandDAO.sumCountProductsForAllPeriod(customers.get(i),
                             products));
                    demand.setTimePeriod(timePeriod);
                    demand.setCustomers(customers.get(i));
                    demand.setProducts(products);
                    if (demand.getCount() != null) {
                        demandForAllPeriodDAO.save(demand);
                    }
                }
            }
        }
    }

    @Override
    public List<Customers> getCurrentCustomers() {
        return historicalDemandDAO.getAllCurrentCustomers();
    }
}
