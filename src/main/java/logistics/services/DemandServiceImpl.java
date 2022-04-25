package logistics.services;


import logistics.DAO.main.DemandDAO;
import logistics.DAO.main.HistoricalDemandDAO;
import logistics.entityes.Demand;
import logistics.entityes.HistoricalDemand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandServiceImpl implements DemandService{

    private DemandDAO demandDAO;
    private HistoricalDemandDAO historicalDemandDAO;

    public DemandServiceImpl(DemandDAO demandDAO, HistoricalDemandDAO historicalDemandDAO){
        this.demandDAO = demandDAO;
        this.historicalDemandDAO = historicalDemandDAO;
    }

    @Override
    public void save() {
        for(HistoricalDemand historicalDemand : historicalDemandDAO.findAll()){
            Demand demand = new Demand();
            demand.setCustomers(historicalDemand.getCustomers());
            demand.setProducts(historicalDemand.getProducts());
            demand.setRevenue(historicalDemand.getPrice());
            demand.setCurrency("RUB");
            demandDAO.save(demand);

        }

    }

    @Override
    public void update() {
        List<Demand> demands = demandDAO.findAll();
        int count = 1;
        while (demands.size()>0){

            List<Demand> newDemands = demands
                    .stream()
                    .filter(demand -> (demand.getProducts().getName().equals(demands.get(0).getProducts().getName())))
                    .filter(demand -> (demand.getCustomers().getName().equals(demands.get(0).getCustomers().getName())))
                    .collect(Collectors.toList());
            for(Demand demand: newDemands){
                Demand demand1 = demandDAO.getById(demand.getId());
                demand1.setCurrency(demand.getCurrency());
                demand1.setProducts(demand.getProducts());
                demand1.setRevenue(demand.getRevenue());
                demand1.setCustomers(demand.getCustomers());
                demand1.setId_demand("demand " + count);
                demandDAO.save(demand1);
            }
            count++;
            demands.removeAll(newDemands);
        }
    }

    @Override
    public void delete() {
        List<Demand> demands = demandDAO.findAll();
        while (demands.size()>0){
            List<Demand> newDemands = demands
                    .stream()
                    .filter(demand -> (demand.getId_demand().equals(demands.get(0).getId_demand())))
                    .collect(Collectors.toList());
            int count = 0;
            for(Demand demand : newDemands){
                if(count!=newDemands.size()-1){
                    demandDAO.delete(demand);
                    count++;
                }
            }
            demands.removeAll(newDemands);
        }
    }
}
