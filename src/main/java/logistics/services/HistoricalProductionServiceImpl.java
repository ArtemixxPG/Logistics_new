package logistics.services;

import logistics.DAO.*;
import logistics.entityes.*;
import logistics.entityes.DCsAndFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HistoricalProductionServiceImpl  implements HistoricalProductionService{

    private HistoricalProductionDAO historicalProductionDAO;
    private ProductDAO productDAO;
    private DC_FactoriesDAO dc_factoriesDAO;
    private TimePeriodDAO timePeriodDAO;
    private BOMDAO bomdao;


    public HistoricalProductionServiceImpl(HistoricalProductionDAO historicalProductionDAO, ProductDAO productDAO,
                                       DC_FactoriesDAO dc_factoriesDAO, TimePeriodDAO timePeriodDAO,
                                           BOMDAO bomdao){
        this.historicalProductionDAO = historicalProductionDAO;
        this.productDAO = productDAO;
        this.dc_factoriesDAO = dc_factoriesDAO;
        this.timePeriodDAO = timePeriodDAO;
        this.bomdao = bomdao;
    }


    @Override
    public Long save(HistoricalProduction historicalProduction, String productName,
                     String factorialName, String station, Date period) {
        Products products = productDAO.getProductsByName(productName);
        historicalProduction.setProducts(products);
        DCsAndFactories dCsAndFactories = dc_factoriesDAO.getDCsAndFactoriesByName("fact_"+factorialName);

        if(dCsAndFactories==null){
            dCsAndFactories = dc_factoriesDAO.getDCsAndFactoriesByName("fact_"+factorialName + " " + station);
        }

        historicalProduction.setdCsAndFactories(dCsAndFactories);

        TimePeriod timePeriod = timePeriodDAO.getTimePeriodByName(period);

        historicalProduction.setTimePeriod(timePeriod);

        return historicalProductionDAO.save(historicalProduction).getId();
    }

    @Override
    public void updateAllBOM() {
        List<HistoricalProduction> historicalProductions = historicalProductionDAO.findAll();
        List<BOM> boms = bomdao.findAll();
        for(HistoricalProduction historicalProduction: historicalProductions){
            for(BOM bom: boms) {
                if (historicalProduction.getProducts() != null && bom.getProducts() != null) {
                    if (historicalProduction.getProducts().getName().equals(bom.getProducts().getName())) {
                        HistoricalProduction historicalProduction1 = historicalProductionDAO.getById(historicalProduction.getId());
                        historicalProduction1.setCountProduct(historicalProduction.getCountProduct());
                        historicalProduction1.setProducts(historicalProduction.getProducts());
                        historicalProduction1.setTimePeriod(historicalProduction.getTimePeriod());
                        historicalProduction1.setdCsAndFactories(historicalProduction.getdCsAndFactories());
                        historicalProduction1.setBom(bom);
                        historicalProductionDAO.save(historicalProduction1);
                    }
                }
            }
        }
    }
}
