package logistics.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.DAO.HistoricDemandDAO;
import logistics.entityes.HistoricDemand;
import logistics.entityes.HistoricalDemand;
import logistics.services.*;
import org.springframework.stereotype.Component;
import sun.font.DelegatingShape;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HistoricalDemandParser {

    private HistoricalDemandService historicalDemandService;
    private DemandForAllPeriodService demandForAllPeriodService;
    private HistoricalDemandWRDService historicalDemandWRDService;
    private HistoricDemandService historicDemandService;

    private DemandService demandService;

    private List<String[]> list;



    private String fileName;

    public HistoricalDemandParser(HistoricalDemandService historicalDemandService,
                                  DemandForAllPeriodService demandForAllPeriodService,
                                  HistoricalDemandWRDService historicalDemandWRDService,
                                  DemandService demandService, HistoricDemandService historicDemandService){
        this.historicalDemandService = historicalDemandService;
        this.demandForAllPeriodService = demandForAllPeriodService;
        this.historicalDemandWRDService = historicalDemandWRDService;
        this.demandService = demandService;
        this.historicDemandService = historicDemandService;

        this.list = new ArrayList<>();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private int prevCount = 0;
    private HistoricalDemand historicalDemand = null;


    public void createDemand(){
        demandService.save();
    }


    public void cloneTable(){
        historicalDemandWRDService.save();
    }

    public void update(){
        demandService.update();
    }

    public void delete(){
        demandService.delete();
    }

    public void newSave(){
        historicDemandService.save();
    }

    public void updateTR(String fileName){
        historicDemandService.updateCountTR(fileName);
    }

    public void parser() throws IOException {
        FileWriter fileWriter = new FileWriter("notes3.txt", false);



        String prevPeriod = "";
        List<Map<String, String>> hdS = new ArrayList<>();
        while (list.size()>0){

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

            Map<String, String> parametrs = new HashMap<>();

            parametrs.put("cust", custName);
            parametrs.put("dateTime", dateTime);
            if(product.contains("пара колесная") ||product.contains("Пара колесная")){
                if(product.contains("Новая")){
                    productName = "Новая ось пары колесной";
                } else if(!product.contains("СОСК")&&!product.contains("СОНК")&&!product.contains("НОСК")
                &&!product.contains("НОНД")){
                    productName = "БУ_"+list.get(0)[3].split(" ")[0] + " пара колесная обод " + gradation;
                } else if(product.contains("СОСК")){
                    productName = "СОСК_"+list.get(0)[3] + " пара колесная обод " + gradation;
                }
                else if(product.contains("НОСК")){
                    productName = "НОСК_"+list.get(0)[3] + " пара колесная обод " + gradation;
                }
                else if(product.contains("НОНД")){
                        productName = "Пара колесная " + "НОНК " + product.split(" ")[product.split(" ").length - 1];

                }
                else if(product.contains("СОНК")){
                    productName = "Пара колесная СОНК";
                }
            }

            if(product.contains("балка надрессорная") || product.contains("рама боковая") ||
                    product.contains("Балка надрессорная") || product.contains("Рама боковая")){
                if(product.contains("Новая")){
                    if(product.contains("балка надрессорная") || product.contains("Балка надрессорная")){
                        productName = "Новая балка надрессорная";
                    } else {
                        productName = "Новая рама боковая";
                    }
                } else if(product.contains("балка надрессорная") || product.contains("Балка надрессорная")){
                    productName = "БУ_"+list.get(0)[3] + " балка надрессорная срок эксплуатации " + gradation;
                } else {
                    productName = "БУ_"+list.get(0)[3] + " рама боковая срок эксплуатации " + gradation;
                }
            }

            if(product.contains("Аппарат") || product.contains("аппарат")){
                if(product.contains("Новая")){
                        productName = "Новый поглощающий аппарат";
                } else if(list.get(0)[3].equals("ОД")){
                    productName = "Поглощающий аппарат ОД";
                } else if(list.get(0)[3].equals("РД")){
                    productName = "Поглощающий аппарат РД";
                }
            }
            if(product.contains("ЦКК")){
                productName = "Новый диск пары колесной (ЦКК)";
            }


            int count = 0;
            for(String[] newl1: newL){
                if(!newl1[8].equals("")) {
                    count += Integer.parseInt(newl1[8]);
                }
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            HistoricalDemand historicalDemand = new HistoricalDemand();
            try {
                historicalDemand.setDate(simpleDateFormat.parse(dateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            historicalDemand.setCount(count);
            if (Character.isDigit(list.get(0)[10].split(" ")[0].charAt(0))
                    && Character.isDigit(list.get(0)[10].split(" ")[1].charAt(0))) {
                historicalDemand.setPrice(Long.parseLong(list.get(0)[10].split(" ")[0] + list.get(0)[10].split(" ")[1]));
            }


            historicalDemandService.save(historicalDemand, productName, custName, list.get(0)[5], fileWriter);

            list.removeAll(newL);


        }






//        for(int i = 0; i <  list.size(); i++){
//            if((!list.get(i)[4].equals(list.get(i+1)[4]) || !list.get(i)[6].equals(list.get(i+1)[6]))) {
//                historicalDemand = new HistoricalDemand();
//
//            String product = list.get(i)[1];
//            boolean findNew = false;
//            for(String object : product.split(" ")){
//                if(object.equals("Новая"))
//                    findNew = true;
//            }
//
//            if(findNew){
//                productName = list.get(i)[1].split(" ")[0] + " " + list.get(i)[1].split(" ")[1];
//                if(productName.equals("Пара колесная")){
//                        productName = list.get(i)[3].split(" ")[0] + " " + productName.toLowerCase() + " " + "обод "+ list.get(i)[2];
//                }
//                  else if(productName.equals("Балка надрессорная")){
//                    productName = "Новая балка надрессорная";
//                } else if(productName.equals("Рама боковая")){
//                    productName = "Новая рама боковая";
//                } else if(productName.equals("Аппарат поглощающий")){
//                    productName = "Новый поглощающий аппарат";
//                }   else if(productName.equals("Ось пары пары колесной")){
//                productName = "Новая ось пары колесной";
//            }
//
//                  else {
//                    productName = list.get(i)[3].split(" ")[0]  + " " + list.get(i)[2];
//                }
//            } else {
//                productName = list.get(i)[1].split(" ")[0] + " " + list.get(i)[1].split(" ")[1];
//                if(productName.equals("Пара колесная")){
//                    if (list.get(i)[3].split(" ")[0].equals("РД")||list.get(i)[3].split(" ")[0].equals("ОД")) {
//                        productName = "БУ_" + list.get(i)[3].split(" ")[0] + " " + productName.toLowerCase() + " " + "обод " + list.get(i)[2];
//                    } else if(list.get(i)[3].split(" ")[0].equals("СОНК")){
//                        productName =   productName +  " " + list.get(i)[3].split(" ")[0];
//                    } else if(list.get(i)[3].split(" ")[0].equals("НОНК") ) {
//                        if(list.get(i)[1].split(" ")[list.get(i)[1].split(" ").length - 1].equals("ТР")){
//                        productName = productName + " " + list.get(i)[3].split(" ")[0]  + list.get(i)[1].split(" ")[list.get(i)[1].split(" ").length - 2];
//                        }
//                        else {
//                            productName = productName + " " + list.get(i)[3].split(" ")[0] + " " + list.get(i)[1].split(" ")[list.get(i)[1].split(" ").length - 1];
//                        }
//                    } else if(list.get(i)[1].split(" ")[2].equals("СОСК")){
//                        productName = list.get(i)[1].split(" ")[2] + "_" + list.get(i)[3].split(" ")[0] +
//                                " " + productName.toLowerCase() + " " + "обод " + list.get(i)[2];
//                    } else if(list.get(i)[1].split(" ").length>5){
//                        if(list.get(i)[3].split(" ")[0].equals("НОНК") || list.get(i)[1].split(" ")[4].equals("НОНД")){
//                            productName = productName + " " + "НОНК ОД" + list.get(i)[1].split(" ")[list.get(i)[1].split(" ").length - 1];
//                        }
//                    }
//                }
//                else if(productName.equals("Балка надрессорная")||productName.equals("Рама боковая")){
//                    productName ="БУ_" + list.get(i)[3].split(" ")[0]  + " " +  productName.toLowerCase() +" "+ "срок эксплуатации" + " "+ list.get(i)[2];
//                } else if((productName.contains("Аппарат поглощающий"))){
//                    productName = "Поглощающий аппарат" + list.get(i)[3].split(" ")[0];
//                }
//                else {
//                    if(list.get(i)[1].split(" ")[list.get(i)[1].split(" ").length - 1].equals("(ЦКК)"))
//                    productName ="Новый диск пары колесной (ЦКК)";
//                }
//            }
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
//
////            if (!demand[8].equals("")) {
////                historicalDemand.setCount(Integer.parseInt(demand[8]));
////            }
//            try {
//                historicalDemand.setDate(simpleDateFormat.parse(list.get(i)[6]));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            if(list.get(i)[10].split(" ").length == 2) {
//                if (!(list.get(i)[10].split(" ")[0] + list.get(i)[10].split(" ")[1]).equals("")) {
//                    historicalDemand.setPrice(Long.parseLong(list.get(i)[10].split(" ")[0] + list.get(i)[10].split(" ")[1]));
//                }
//            }
//
//
//
//
//            }
//            if(list.get(i)[4].equals(list.get(i+1)[4]) ||  list.get(i)[6].equals(list.get(i+1)[6])){
//                if (!list.get(i)[8].equals("")) {
//                    int currentCount = 0;
//                    if(!list.get(i)[8].equals("")){
//                        currentCount = Integer.parseInt(list.get(i)[8]);
//                    }
//                    int sumCount = prevCount + currentCount;
//                    prevCount = sumCount;
//          }
//
//            }
//            if((!list.get(i)[4].equals(list.get(i+1)[4])|| !list.get(i)[6].equals(list.get(i+1)[6]))) {
//                int currentCount = 0;
//                if(!list.get(i)[8].equals("")){
//                    currentCount = Integer.parseInt(list.get(i)[8]);
//                }
//                int sumCount = prevCount + currentCount;
//                historicalDemand.setCount(sumCount);
//                historicalDemandService.save(historicalDemand, productName, list.get(i)[4], list.get(i)[5], fileWriter);
//                prevCount = 0;
//            }
//
//        }
        fileWriter.flush();
    }


    public void createListCountForAllPeriod(){
        demandForAllPeriodService.save();
    }
    public void createHistoricDemand(String fileName){
        historicDemandService.save(fileName);
    }

    public void initPrevData(){
        Reader reader = null;
        try {
            try {
                reader = new InputStreamReader(new FileInputStream(this.fileName), "cp1251");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVReader csvReader = new CSVReader(reader);
        try {
            list = csvReader.readAll();
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

        list.remove(0);



    }

}
