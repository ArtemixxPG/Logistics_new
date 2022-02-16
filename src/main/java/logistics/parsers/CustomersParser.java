package logistics.parsers;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.entityes.Customers;
import logistics.entityes.DCsAndFactories;
import logistics.entityes.Suppliers;
import logistics.services.CustomersService;
import logistics.services.DCsAndFactoriesService;
import logistics.services.LocationService;
import logistics.services.SuppliersService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomersParser {

    private CustomersService customersService;
    private SuppliersService suppliersService;
    private DCsAndFactoriesService dCsAndFactoriesService;

    private LocationService locationService;

    private List<String[]> customers;

    private List<Customers> fillCustomers;
    private List<Suppliers> fillSuppliers;
    private List<DCsAndFactories> fillDcsAndFactories;


    public CustomersParser(CustomersService customersService, SuppliersService suppliersService,
                           DCsAndFactoriesService dCsAndFactoriesService){
        this.customersService = customersService;
        this.suppliersService = suppliersService;
        this.dCsAndFactoriesService = dCsAndFactoriesService;



        this.customers = new ArrayList<>();
        this.fillCustomers = new ArrayList<>();
        this.fillSuppliers = new ArrayList<>();
        this.fillDcsAndFactories = new ArrayList<>();
    }
    public void parse(){
        for(String[] customer : customers){

            if(!customer[0].equals("")) {
                Customers customers = new Customers();
                customers.setName("cust_" + customer[0]);
                fillCustomers.add(customers);
            }




//            if(!customer[0].equals("")) {
//                Suppliers suppliers = new Suppliers();
//                suppliers.setName("sup_" + customer[0]);
//                fillSuppliers.add(suppliers);
//            }
//
//            if(!customer[2].equals("")) {
//                DCsAndFactories dCsAndFactories = new DCsAndFactories();
//                dCsAndFactories.setName("dc_" + customer[2]);
//                fillDcsAndFactories.add(dCsAndFactories);
//            }
//
//            if(!customer[3].equals("")) {
//                DCsAndFactories dCsAndFactories = new DCsAndFactories();
//                dCsAndFactories.setName("fact_" + customer[3]);
//                fillDcsAndFactories.add(dCsAndFactories);
//            }
//
//            if(!customer[4].equals("")) {
//                DCsAndFactories dCsAndFactories = new DCsAndFactories();
//                dCsAndFactories.setName("fcheck_" + customer[3]);
//                fillDcsAndFactories.add(dCsAndFactories);
//            }
        }
    }

    public void parseExternal() {
        for (String[] customer : customers) {

            if (!customer[5].equals("")) {
                Suppliers suppliers = new Suppliers();
                suppliers.setName("extsup_" + customer[5]);
                fillSuppliers.add(suppliers);
            }
        }
    }

    public void getPostgree(){
        for (Customers customers: fillCustomers) {
            customersService.save(customers);

        }


        for (Suppliers suppliers: fillSuppliers) {
            suppliersService.save(suppliers);
        }

        for (DCsAndFactories dCsAndFactories: fillDcsAndFactories) {
            dCsAndFactoriesService.save(dCsAndFactories);
        }
    }


    public void initData(String fileName){
        Reader reader = null;
        try {
            try {
                reader = new InputStreamReader(new FileInputStream(fileName), "cp1251");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVReader csvReader = new CSVReader(reader);
        try {
            customers = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

      //  customers.remove(0);
    }
}
