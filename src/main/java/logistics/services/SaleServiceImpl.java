package logistics.services;

import logistics.DAO.main.*;
import logistics.entityes.Demand;
import logistics.entityes.HistoricDemand;
import logistics.entityes.HistoricSale;
import logistics.entityes.Sale;
import logistics.initialization.InitCSV;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService{


    private SaleDAO saleDAO;
    private HistoricSaleDAO historicSaleDAO;
    private ProductDAO productDAO;


    private long id;

    public SaleServiceImpl(SaleDAO saleDAO, HistoricSaleDAO historicSaleDAO,
                           TimePeriodDAO timePeriodDAO, ProductDAO productDAO, CustomersDao customerDao){
        this.saleDAO = saleDAO;
        this.historicSaleDAO = historicSaleDAO;

        this.productDAO = productDAO;

    }


    @Override
    public void save(String fileName) {
        InitCSV initCSV = InitCSV.init(fileName);
        List<String[]> list = initCSV.getList();

        while (list.size() > 0) {
             String custName = list.get(0)[7];
            String delivery = list.get(0)[3];
            String dateTime = list.get(0)[5];
            String product = list.get(0)[11];
            String gradation = "";
            String prim = "";
            int diapozon = 0;
            boolean withOutGradation = false;
            boolean withSeries = false;


            int period = 0;

            int begin = 0;
            int end = 0;

            if (!product.contains("Аппарат поглощающий")) {

                if (list.get(0)[12].split(",")[0].length() == 0) {
                    if (list.get(0)[13].split(",")[0].length() != 0) {
                        gradation = list.get(0)[13];
                    } else {
                        int current = 2022;
                        if (!list.get(0)[15].split(" ")[0].isEmpty()) {
                            withSeries = true;
                            period = current - Integer
                                    .parseInt(list.get(0)[15].split(" ")[list.get(0)[15].split(" ").length - 1]);
                            if (period <= 10) {
                                begin = 0;
                                gradation = "1-5 лет";
                                end = 10;
                            }
                            if (period > 10 && period <= 15) {
                                begin = 11;
                                gradation = "11-15 лет";
                                end = 15;
                            }
                            if (period > 15 && period <= 20) {
                                begin = 16;
                                gradation = "16-20 лет";
                                end = 20;
                            }
                            if (period > 20 && period <= 25) {
                                begin = 21;
                                gradation = "21-25 лет";
                                end = 25;
                            }
                            if (period > 25 && period <= 30) {
                                begin = 26;
                                gradation = "26-30 лет";
                                end = 30;
                            }
                            if (period > 30 && period <= 32) {
                                begin = 31;
                                gradation = "31-32 лет";
                                end = 32;
                            }
                        } else {
                            withOutGradation = true;
                            gradation = "1-5 лет";

                        }
                    }

                }
                if (list.get(0)[12].split(",").length > 2) {
                    gradation = list.get(0)[12].split(",")[0];
                    prim = list.get(0)[12].split(",")[1];
                } else if (list.get(0)[12].split(",").length == 2) {
                    gradation = list.get(0)[12].split(",")[0];
                }
            }

            String finalGradation = gradation;



            diapozon  = period;

            String productName = "";
            List<String[]> newL = null;
            if(withOutGradation) {
                newL = list.stream()
                        .filter(s -> (s[7].equals(custName)))
                        .filter(s -> (s[3].equals(delivery)))
                        .filter(s -> (s[11].equals(product)))
                        .filter(s -> (s[12].split(",")[0].equals("")))
                        .collect(Collectors.toList());
            } else if(withSeries) {
                if(diapozon <= 32) {
                    String data = list.get(0)[5];
                    int finalSrok = 0;
                    int finalBegin = begin;
                    int finalEnd = end;
                    newL = list.stream()
                            .filter(s -> (s[7].equals(custName)))
                            .filter(s -> (s[3].equals(delivery)))
                            .filter(s -> (s[11].equals(product)))
                            .filter(s -> (s[5].equals(data)))
                            .filter(s -> (!s[15].equals("")))
                            .filter(s -> (((finalBegin <= 2022 - Integer
                                    .parseInt(s[15].split(" ")[s[15]
                                            .split(" ").length - 1])) && (2022 - Integer
                                    .parseInt(s[15].split(" ")[s[15].split(" ").length - 1]) <= finalEnd)))
                                    &&(!s[15].split(" ")[s[15]
                                    .split(" ").length - 1].equals("")))
                            .collect(Collectors.toList());
                }
            } else {
                newL = list.stream()
                        .filter(s -> (s[7].equals(custName)))
                        .filter(s -> (s[3].equals(delivery)))
                        .filter(s -> (s[11].equals(product)))
                        .filter(s -> (s[12].split(",")[0].equals(finalGradation) || (s[13].equals(finalGradation))))
                        .collect(Collectors.toList());
            }

            if (product.contains("пара колесная") || product.contains("Пара колесная")) {
                if (product.contains("Новая")) {
                    productName = "Новая ось пары колесной";
                } else if (!product.contains("СОСК") && !product.contains("СОНК") && !product.contains("НОСК")
                        && !product.contains("НОНД")) {
                    productName = "БУ_" + list.get(0)[11].split(" ")[2] + " пара колесная обод " + gradation;
                } else if (product.contains("СОСК")) {
                    productName = "СОСК_ОД" + " пара колесная обод " + gradation;
                } else if (product.contains("НОСК")) {
                    productName = "НОСК_ОД" + " пара колесная обод " + gradation;
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
                    productName = "БУ_" + product.split(" ")[2] + " балка надрессорная срок эксплуатации " + gradation;
                } else {
                    productName = "БУ_" + product.split(" ")[2] + " рама боковая срок эксплуатации " + gradation;
                }
            }

            if (product.contains("Аппарат") || product.contains("аппарат")) {
                if (product.contains("Новая")) {
                    productName = "Новый поглощающий аппарат";
                } else if (product.split(" ")[product.split(" ").length-1].equals("ОД")) {
                    productName = "Поглощающий аппарат ОД";
                } else if (product.split(" ")[product.split(" ").length-1].equals("РД")) {
                    productName = "Поглощающий аппарат РД";
                }
            }
            if (product.contains("ЦКК")) {
                productName = "Новый диск пары колесной (ЦКК)";
            }
            Sale sale = null;

            if(saleDAO.getSaleByProductsANDCustomers(productName, "cust_"+custName)==null){
                id++;
                sale = new Sale();
                sale.setProducts(productDAO.getProductsByName(productName));
                sale.setCustomers("cust_"+custName);
                sale.setPrim(prim);
                sale.setPriority(list.get(0)[6]);
                sale.setId_sale("sale_"+id);
                saleDAO.save(sale);

            } else {
                sale = saleDAO.getSaleByProductsANDCustomers(productName, "cust_"+custName);
            }





            long count_fact = 0;
            long count_plan =0;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            HistoricSale historicSale = new HistoricSale();

            if(newL != null) {
                for (String[] newl1 : newL) {

                    if (!newl1[16].equals("") && !newl1[16].contains("-")) {
                        count_plan += Long.parseLong(newl1[16]);
                    } else {
                        count_plan = 0;
                    }

                    if (!newl1[17].equals("") && !newl1[17].contains("-")) {
                        count_fact += Long.parseLong(newl1[17]);
                    } else {
                        count_fact = 0;
                    }

                }


                historicSale.setCount_fact(count_fact);
                historicSale.setCount_plan(count_plan);


                try {
                    historicSale.setTimePeriod(simpleDateFormat.parse(dateTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (sale != null) {
                    historicSale.setSale(sale);
                    historicSaleDAO.save(historicSale);
                }

                list.removeAll(newL);
            } else {
                list.remove(list.get(0));
            }
        }
    }
}
