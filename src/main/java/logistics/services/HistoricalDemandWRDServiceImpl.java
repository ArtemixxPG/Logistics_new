package logistics.services;

import logistics.DAO.main.HistoricalDemandDAO;
import logistics.DAO.main.HistoricalDemandWithOutRDDAO;
import logistics.entityes.HistoricalDemand;
import logistics.entityes.HistoricalDemandWithoutRD;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HistoricalDemandWRDServiceImpl implements HistoricalDemandWRDService {

    private HistoricalDemandWithOutRDDAO historicalDemandWithOutRDDAO;
    private HistoricalDemandDAO historicalDemandDAO;


    public HistoricalDemandWRDServiceImpl(HistoricalDemandDAO historicalDemandDAO,
                                          HistoricalDemandWithOutRDDAO historicalDemandWithOutRDDAO){
        this.historicalDemandDAO = historicalDemandDAO;
        this.historicalDemandWithOutRDDAO = historicalDemandWithOutRDDAO;
    }




    @Override
    public void save() {
        for(HistoricalDemand historicalDemand : historicalDemandDAO.findAll()){
            HistoricalDemandWithoutRD historicalDemandWithoutRD = new HistoricalDemandWithoutRD();
            historicalDemandWithoutRD.setPrice(historicalDemand.getPrice());
            historicalDemandWithoutRD.setCount(historicalDemand.getCount());
            historicalDemandWithoutRD.setCustomers(historicalDemand.getCustomers());
            historicalDemandWithoutRD.setProducts(historicalDemand.getProducts());
            historicalDemandWithoutRD.setDate(historicalDemand.getDate());
            historicalDemandWithOutRDDAO.save(historicalDemandWithoutRD);
        }
    }
}
