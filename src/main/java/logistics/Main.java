package logistics;

import logistics.parsers.*;
import logistics.services.CustAndDCProductService;
import logistics.services.CustAndDCProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = {"entityes", "entityes.results"})
public class Main  {




    public void printList(String f){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConsoleConfig.class);
        LocationParser locationParser = applicationContext.getBean(LocationParser.class);
        CustomersParser customersParser = applicationContext.getBean(CustomersParser.class);
        ProductParser productParser = applicationContext.getBean(ProductParser.class);
        HistoricalDemandParser historicalDemandParser = applicationContext.getBean(HistoricalDemandParser.class);
        HistoricalProductionParser historicalProductionParser = applicationContext.getBean(HistoricalProductionParser.class);
        CustAndDCProductService custAndDCProductService = applicationContext.getBean(CustAndDCProductServiceImpl.class);
        NEparser nEparser = applicationContext.getBean(NEparser.class);
//        customersParser.initData(f);
//        customersParser.parse();
//        //customersParser.parseExternal();
//        customersParser.getPostgree();
//       locationParser.setFileName(f);
//        locationParser.initPrevData();
//        locationParser.initData();
//        locationParser.getPostgree();
//        productParser.setFileName(f);
//        productParser.initPrevData();
//        productParser.parser();
//        historicalDemandParser.setFileName(f);
//        historicalDemandParser.initPrevData();
//        try {
//            historicalDemandParser.parser();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        historicalProductionParser.setFileNameHP(f);
//        historicalProductionParser.initPrevDataHP();
//        try {
//            historicalProductionParser.parse();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

      //  historicalDemandParser.createDemand();
      //  historicalDemandParser.createHistoricDemand(f);

       // historicalDemandParser.createListCountForAllPeriod();
       // custAndDCProductService.save();
        //historicalDemandParser.cloneTable();
       nEparser.setFilename(f);
       nEparser.initPrevData();
       nEparser.parserSS();
       // historicalProductionParser.parseBom();
      //  historicalDemandParser.update();
       // historicalDemandParser.delete();
//        historicalDemandParser.newSave();
     //   historicalDemandParser.updateTR(f);
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
//        Main main = new Main();
//        for(String file : args)
//        main.printList(file);

    }


}
