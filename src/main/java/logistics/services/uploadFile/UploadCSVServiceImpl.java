package logistics.services.uploadFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import logistics.DAO.main.*;
import logistics.entityes.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@Transactional
public class UploadCSVServiceImpl implements UploadCSVService{


    private TimePeriodDAO timePeriodDAO;

    //private CustAndDCProductDAO custAndDCProductDAO;
    private CustomersDAO customersDAO;
    private DC_FactoriesDAO dc_factoriesDAO;
    private DemandDAO demandDAO;
    //private HistoricalDemandDAO historicalDemandDAO;
    //private HistoricalProductionDAO historicalProductionDAO;
    private LocationsDAO locationsDAO;
    private ProductDAO productDAO;
    private SaleDAO saleDAO;
    private SuppliersDAO suppliersDAO;


    public UploadCSVServiceImpl(TimePeriodDAO timePeriodDAO) {
        this.timePeriodDAO = timePeriodDAO;
    }
    //public UploadCSVServiceImpl(CustAndDCProductDAO custAndDCProductDAO) { this.custAndDCProductDAO = custAndDCProductDAO; }
    public UploadCSVServiceImpl(CustomersDAO customersDAO) { this.customersDAO = customersDAO; }
    public UploadCSVServiceImpl(DC_FactoriesDAO dc_factoriesDAO) { this.dc_factoriesDAO = dc_factoriesDAO; }
    public UploadCSVServiceImpl(DemandDAO demandDAO) { this.demandDAO = demandDAO; }
    //public UploadCSVServiceImpl(HistoricalDemandDAO historicalDemandDAO) { this.historicalDemandDAO = historicalDemandDAO; }
    //public UploadCSVServiceImpl(HistoricalProductionDAO historicalProductionDAO) { this.historicalProductionDAO = historicalProductionDAO; }
    public UploadCSVServiceImpl(LocationsDAO locationsDAO) { this.locationsDAO = locationsDAO; }
    public UploadCSVServiceImpl(ProductDAO productDAO) { this.productDAO = productDAO; }
    public UploadCSVServiceImpl(SaleDAO saleDAO) { this.saleDAO = saleDAO; }
    public UploadCSVServiceImpl(SuppliersDAO suppliersDAO) { this.suppliersDAO = suppliersDAO; }


    @Override
    public void upload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("");
            System.out.println("Please select a CSV file to upload.");
        } else {
            if (file.getOriginalFilename().contains("periods.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<TimePeriod> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(TimePeriod.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<TimePeriod> timePeriods = csvToBean.parse();
                    timePeriodDAO.saveAll(timePeriods);

                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("customers.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Customers> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Customers.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Customers> customers = csvToBean.parse();
                    customersDAO.saveAll(customers);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("dcs and factories.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<DCsAndFactories> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(DCsAndFactories.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<DCsAndFactories> dCsAndFactories = csvToBean.parse();
                    dc_factoriesDAO.saveAll(dCsAndFactories);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("demand.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Demand> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Demand.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Demand> demands = csvToBean.parse();
                    demandDAO.saveAll(demands);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            /*if (file.getOriginalFilename().contains("HistoricalDemand.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<HistoricalDemand> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(HistoricalDemand.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<HistoricalDemand> historicalDemands = csvToBean.parse();
                    historicalDemandDAO.saveAll(historicalDemands);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }*/
            /*if (file.getOriginalFilename().contains("HistoricalProduction.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<HistoricalProduction> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Customers.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<HistoricalProduction> historicalProductions = csvToBean.parse();
                    historicalProductionDAO.saveAll(historicalProductions);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }*/
            if (file.getOriginalFilename().contains("locations.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Locations> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Locations.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Locations> locations = csvToBean.parse();
                    locationsDAO.saveAll(locations);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("products.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Products> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Products.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Products> products = csvToBean.parse();
                    productDAO.saveAll(products);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            /*if (file.getOriginalFilename().contains("CustAndDCProducts.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<CustAndDCProduct> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(CustAndDCProduct.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<CustAndDCProduct> custAndDCProducts = csvToBean.parse();
                    custAndDCProductDAO.saveAll(custAndDCProducts);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }*/
            if (file.getOriginalFilename().contains("sale.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Sale> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Sale.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Sale> sales = csvToBean.parse();
                    saleDAO.saveAll(sales);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("suppliers.csv")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Suppliers> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Suppliers.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Suppliers> suppliers = csvToBean.parse();
                    suppliersDAO.saveAll(suppliers);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
        }
        }
}
