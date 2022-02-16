package logistics.services;

import logistics.DAO.CustAndDCProductDAO;
import logistics.DAO.CustomersDao;
import logistics.DAO.DC_FactoriesDAO;
import logistics.DAO.HistoricalProductionDAO;
import logistics.entityes.*;
import logistics.entityes.Customers;
import logistics.entityes.DCsAndFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustAndDCProductServiceImpl implements CustAndDCProductService{

    private CustAndDCProductDAO custAndDCProductDAO;
    private CustomersDao customersDao;
    private DC_FactoriesDAO dc_factoriesDAO;
    private HistoricalProductionDAO historicalProductionDAO;



    public CustAndDCProductServiceImpl(CustAndDCProductDAO custAndDCProductDAO, CustomersDao customersDao,
                                       DC_FactoriesDAO dc_factoriesDAO, HistoricalProductionDAO historicalProductionDAO){

        this.custAndDCProductDAO = custAndDCProductDAO;
        this.customersDao = customersDao;
        this.dc_factoriesDAO = dc_factoriesDAO;
        this.historicalProductionDAO = historicalProductionDAO;
    }


    @Override
    public void save() {
        for(Customers customers : customersDao.findAll()){
            DCsAndFactories dCsAndFactories = dc_factoriesDAO.getDCsAndFactoriesByName("fact_"+customers.getName().split("_")[1]);
            if(dCsAndFactories!= null){
                        if (!historicalProductionDAO.getProductByDCName(dCsAndFactories
                        ).isEmpty()) {
                            for (Products products : historicalProductionDAO.getProductByDCName(dCsAndFactories)) {
                                if(products!=null) {
                                    if(custAndDCProductDAO.getCustAndDCProduct(products, dCsAndFactories, customers) == null) {
                                        CustAndDCProduct custAndDCProduct = new CustAndDCProduct();
                                        custAndDCProduct.setCustomers(customers);
                                        custAndDCProduct.setdCsAndFactories(dCsAndFactories);
                                        custAndDCProduct.setProducts(products);

                                        custAndDCProductDAO.save(custAndDCProduct);
                                    }
                                }

                    }
                }
            }
        }

    }
}
