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
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@Transactional
public class UploadCSVServiceImpl implements UploadCSVService{


    private TimePeriodDAO timePeriodDAO;
    private BOMDAO bomDAO;
    private CustomersDAO customersDAO;
    private DC_FactoriesDAO dc_factoriesDAO;
    private DemandDAO demandDAO;
    private LocationsDAO locationsDAO;
    private ProductDAO productDAO;
    private SaleDAO saleDAO;
    private SuppliersDAO suppliersDAO;


    public UploadCSVServiceImpl(TimePeriodDAO timePeriodDAO, CustomersDAO customersDAO,
                                DC_FactoriesDAO dc_factoriesDAO, DemandDAO demandDAO, SuppliersDAO suppliersDAO,
                                LocationsDAO locationsDAO, ProductDAO productDAO, SaleDAO saleDAO, BOMDAO bomDAO) {
        this.timePeriodDAO = timePeriodDAO;
        this.customersDAO = customersDAO;
        this.dc_factoriesDAO = dc_factoriesDAO;
        this.demandDAO = demandDAO;
        this.locationsDAO = locationsDAO;
        this.productDAO = productDAO;
        this.saleDAO = saleDAO;
        this.suppliersDAO = suppliersDAO;
        this.bomDAO = bomDAO;
    }



    @Override
    public void upload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("");
            System.out.println("Please select a CSV file to upload.");
        } else if (file.getOriginalFilename().contains(".csv")) {
            //Вынести проверку на csv вверх, после проверки на заполненность файла, оставить только так, как в первом
            if (file.getOriginalFilename().contains("periods")) {
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
            if (file.getOriginalFilename().contains("customers")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Customers> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Customers.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Customers> customers = csvToBean.parse();
                    for(Customers customer : customers){
                        Locations location = locationsDAO.getLocationsByName(customer.getLocationName());
                        customer.setLocations(location);
                        customer.setName("cust_" + customer.getName());
                    }

                    customersDAO.saveAll(customers);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("dcs and factories")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<DCsAndFactories> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(DCsAndFactories.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<DCsAndFactories> dCsAndFactories = csvToBean.parse();
                    for(DCsAndFactories dCsAndFactory : dCsAndFactories){
                        Locations location = locationsDAO.getLocationsByName(dCsAndFactory.getLocationName());
                        dCsAndFactory.setLocations(location);
                        if (dCsAndFactory.getTypeName().contains("производство")) {
                            dCsAndFactory.setName("fact_имя" + dCsAndFactory.getName());
                        }
                        if (dCsAndFactory.getTypeName().contains("сортировочная станция")) {
                            dCsAndFactory.setName("fcheck_имя" + dCsAndFactory.getName());
                        }
                        if (dCsAndFactory.getTypeName().contains("склад")) {
                            dCsAndFactory.setName("dc_имя" + dCsAndFactory.getName());
                        }
                    }

                    dc_factoriesDAO.saveAll(dCsAndFactories);

                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("demand")) {
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
            if (file.getOriginalFilename().contains("locations")) {
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
            if (file.getOriginalFilename().contains("products")) {
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
            if (file.getOriginalFilename().contains("sale")) {
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
            if (file.getOriginalFilename().contains("suppliers")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<Suppliers> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(Suppliers.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<Suppliers> suppliers = csvToBean.parse();
                    for(Suppliers supplier : suppliers){
                        Locations location = locationsDAO.getLocationsByName(supplier.getLocationsName());
                        supplier.setLocations(location);
                        supplier.setName("sup_" + supplier.getName());
                    }

                    suppliersDAO.saveAll(suppliers);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
            if (file.getOriginalFilename().contains("bom")) {
                try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    CsvToBean<BOM> csvToBean = new CsvToBeanBuilder(reader)
                            .withType(BOM.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
                    List<BOM> bom = csvToBean.parse();
                    bomDAO.saveAll(bom);
                } catch (IOException e) {
                    System.out.println("An error occurred while processing the CSV file.");
                    e.printStackTrace();
                }
            }
        }
        }
}
