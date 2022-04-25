package logistics;

import logistics.entityes.HistoricSale;
import logistics.initialization.InitTXT;
import logistics.parsers.*;
import logistics.services.CustAndDCProductService;
import logistics.services.CustAndDCProductServiceImpl;
import logistics.services.SaleService;
import logistics.services.SaleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = {"entityes", "entityes.results"})
public class Main  {




    public void printList(String... f){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConsoleConfig.class);
        LocationParser locationParser = applicationContext.getBean(LocationParser.class);
        CustomersParser customersParser = applicationContext.getBean(CustomersParser.class);
        ProductParser productParser = applicationContext.getBean(ProductParser.class);
        HistoricalDemandParser historicalDemandParser = applicationContext.getBean(HistoricalDemandParser.class);
        HistoricalProductionParser historicalProductionParser = applicationContext.getBean(HistoricalProductionParser.class);
        CustAndDCProductService custAndDCProductService = applicationContext.getBean(CustAndDCProductServiceImpl.class);
        NEparser nEparser = applicationContext.getBean(NEparser.class);
        SIMparser siMparser = applicationContext.getBean(SIMparser.class);
        SaleService saleService = applicationContext.getBean(SaleServiceImpl.class);

        saleService.save(f[0]);
//        siMparser.setFileName(f[0]);
//        siMparser.parseProductFlowsFromTXT();
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
        Main main = new Main();
        main.printList(args[0]);

    }


}
