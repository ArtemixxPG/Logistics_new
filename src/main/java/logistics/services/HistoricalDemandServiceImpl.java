package logistics.services;

import logistics.DAO.CustomersDao;
import logistics.DAO.HistoricalDemandDAO;
import logistics.DAO.ProductDAO;
import logistics.entityes.Customers;
import logistics.entityes.HistoricalDemand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;


@Service
@Transactional
public class HistoricalDemandServiceImpl implements HistoricalDemandService {

    private HistoricalDemandDAO historicalDemandDAO;
    private ProductDAO productDAO;
    private CustomersDao customersDao;

    private String prevNameCust = "";

    public HistoricalDemandServiceImpl(HistoricalDemandDAO historicalDemandDAO, ProductDAO productDAO,
                                       CustomersDao customersDao) {
        this.historicalDemandDAO = historicalDemandDAO;
        this.productDAO = productDAO;
        this.customersDao = customersDao;
    }

    @Override
    public Long save(HistoricalDemand historicalDemand, String productName, String customerName,
                     String station, FileWriter writer) throws IOException {

        Customers customers = customersDao.getCustomersByName("cust_" + customerName);
        if(customers == null){
             customers = customersDao.getCustomersByName("cust_" + customerName + " " + station);
        }

         historicalDemand.setCustomers(customers);
        historicalDemand.setProducts(productDAO.getProductsByName(productName));
        if(historicalDemandDAO.getByCustomersAndProducts(historicalDemand.getCustomers(), historicalDemand.getProducts(),
                 historicalDemand.getPrice(), historicalDemand.getCount()).isEmpty()) {

            return historicalDemandDAO.save(historicalDemand).getId();
        }
        return null;
    }
}
