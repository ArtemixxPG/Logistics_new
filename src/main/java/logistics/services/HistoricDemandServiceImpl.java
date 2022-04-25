package logistics.services;

import logistics.DAO.main.DemandDAO;
import logistics.DAO.main.HistoricDemandDAO;
import logistics.DAO.main.HistoricalDemandDAO;
import logistics.DAO.main.TimePeriodDAO;
import logistics.entityes.Demand;
import logistics.entityes.HistoricDemand;
import logistics.entityes.HistoricalDemand;
import logistics.initialization.InitCSV;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class HistoricDemandServiceImpl implements HistoricDemandService {

    private HistoricDemandDAO historicDemandDAO;
    private DemandDAO demandDAO;
    private TimePeriodDAO timePeriodDAO;
    private HistoricalDemandDAO historicalDemandDAO;


    public HistoricDemandServiceImpl(HistoricDemandDAO historicDemandDAO, DemandDAO demandDAO,
                                     TimePeriodDAO timePeriodDAO, HistoricalDemandDAO historicalDemandDAO){
        this.demandDAO = demandDAO;
        this.historicDemandDAO = historicDemandDAO;
        this.timePeriodDAO = timePeriodDAO;
        this.historicalDemandDAO = historicalDemandDAO;
    }

    @Override
    public void save(String fileName) {
        InitCSV initCSV = InitCSV.init(fileName);
        List<String[]> list = initCSV.getList();

        while (list.size() > 0) {
            String custName = list.get(0)[4];
            String dateTime = list.get(0)[6];
            String product = list.get(0)[1];
            String gradation = list.get(0)[2];
            String productName = "";
            List<String[]> newL = list.stream()
                    .filter(a -> (a[4].equals(custName)))
                    .filter(a -> (a[6].equals(dateTime)))
                    .filter(a -> (a[1].equals(product)))
                    .filter(a -> (a[2].equals(gradation)))
                    .collect(Collectors.toList());

            if (product.contains("пара колесная") || product.contains("Пара колесная")) {
                if (product.contains("Новая")) {
                    productName = "Новая ось пары колесной";
                } else if (!product.contains("СОСК") && !product.contains("СОНК") && !product.contains("НОСК")
                        && !product.contains("НОНД")) {
                    productName = "БУ_" + list.get(0)[3].split(" ")[0] + " пара колесная обод " + gradation;
                } else if (product.contains("СОСК")) {
                    productName = "СОСК_" + list.get(0)[3] + " пара колесная обод " + gradation;
                } else if (product.contains("НОСК")) {
                    productName = "НОСК_" + list.get(0)[3] + " пара колесная обод " + gradation;
                } else if (product.contains("НОНД")) {
                    productName = "Пара колесная " + "НОНК " + product.split(" ")[product.split(" ").length - 1];

                } else if (product.contains("СОНК")) {
                    productName = "Пара колесная СОНК";
                }
            }

            if (product.contains("балка надрессорная") || product.contains("рама боковая") ||
                    product.contains("Балка надрессорная") || product.contains("Рама боковая")) {
                if (product.contains("Новая")) {
                    if (product.contains("балка надрессорная") || product.contains("Балка надрессорная")) {
                        productName = "Новая балка надрессорная";
                    } else {
                        productName = "Новая рама боковая";
                    }
                } else if (product.contains("балка надрессорная") || product.contains("Балка надрессорная")) {
                    productName = "БУ_" + list.get(0)[3] + " балка надрессорная срок эксплуатации " + gradation;
                } else {
                    productName = "БУ_" + list.get(0)[3] + " рама боковая срок эксплуатации " + gradation;
                }
            }

            if (product.contains("Аппарат") || product.contains("аппарат")) {
                if (product.contains("Новая")) {
                    productName = "Новый поглощающий аппарат";
                } else if (list.get(0)[3].equals("ОД")) {
                    productName = "Поглощающий аппарат ОД";
                } else if (list.get(0)[3].equals("РД")) {
                    productName = "Поглощающий аппарат РД";
                }
            }
            if (product.contains("ЦКК")) {
                productName = "Новый диск пары колесной (ЦКК)";
            }


            int count = 0;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            for (String[] newl1 : newL) {
                HistoricDemand historicDemand = new HistoricDemand();
                if (!newl1[8].equals("")) {
                    historicDemand.setCount((long) Integer.parseInt(newl1[8]));
                }
                List<Demand> demand = null;
                Long revenue = null;
                if (Character.isDigit(list.get(0)[10].split(" ")[0].charAt(0))
                        && Character.isDigit(list.get(0)[10].split(" ")[1].charAt(0))) {
                    revenue = Long.parseLong(list.get(0)[10].split(" ")[0] + list.get(0)[10].split(" ")[1]);
                }

                    demand = demandDAO.getDemandByCustomersAndAndProducts("cust_" + custName, productName);

                if (demand == null) {

                        demand = demandDAO.getDemandByCustomersAndAndProducts("cust_" + custName + " " + list.get(0)[5],
                                productName);



                }


                try {
                    historicDemand.setTimePeriod(timePeriodDAO.getTimePeriodByName(simpleDateFormat.parse(dateTime)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(!demand.isEmpty()) {
                    historicDemand.setDemand(demand.get(0));
                    historicDemandDAO.save(historicDemand);
                }

            }



            list.removeAll(newL);
        }
    }

    @Override
    public void save() {
        List<Demand> demands = demandDAO.findAll();
        List<HistoricalDemand> historicalDemands = historicalDemandDAO.findAll();
       while (demands.size()>0) {
            List<Demand> groupDemands = demands
                    .stream()
                    .filter(demand -> (demand.getId_demand().equals(demands.get(0).getId_demand())))
                    .collect(Collectors.toList());
            String custName = groupDemands.get(0).getCustomers().getName();
            String productName = groupDemands.get(0).getProducts().getName();


            List<HistoricalDemand> groupHDs = historicalDemands
                    .stream()
                    .filter(historicalDemand -> (historicalDemand.getCustomers().getName().equals(custName)))
                    .filter(historicalDemand -> (historicalDemand.getProducts().getName().equals(productName)))
                    .collect(Collectors.toList());
            while(groupHDs.size()>0){
                List <HistoricalDemand> groupHDsForPeriod = groupHDs
                        .stream()
                        .filter(historicalDemand -> (historicalDemand.getDate().equals(groupHDs.get(0).getDate())))
                        .collect(Collectors.toList());
                Long count = 0L;
                for(HistoricalDemand historicalDemand:groupHDsForPeriod){
                    count+=historicalDemand.getCount();
                }

                    HistoricDemand historicDemand = new HistoricDemand();
                    historicDemand.setDemand(demandDAO.getDemandByCustomersAndAndProducts(custName, productName).get(0));
                    historicDemand.setCount(count);
                    historicDemand.setTimePeriod(timePeriodDAO.getTimePeriodByName(groupHDsForPeriod.get(0).getDate()));
                    historicDemandDAO.save(historicDemand);
                    groupHDs.removeAll(groupHDsForPeriod);
            }
            demands.removeAll(groupDemands);
        }
    }

    @Override
    public void updateCountTR(String fileName) {
        InitCSV initCSV = InitCSV.init(fileName);
        List<String[]> list = initCSV.getList();
        List<String[]> listTr = list
                .stream()
                .filter(strings -> (strings[1].contains("ТР")))
                .collect(Collectors.toList());
        System.out.println(listTr);


    }
}
