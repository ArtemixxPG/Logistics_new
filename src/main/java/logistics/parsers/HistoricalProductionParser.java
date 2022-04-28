package logistics.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.entityes.BOM;
import logistics.entityes.HistoricalProduction;
import logistics.entityes.TimePeriod;
import logistics.services.BOMService;
import logistics.services.HistoricalProductionService;
import logistics.services.TimePeriodService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class HistoricalProductionParser {

    private List<String[]> list;

    private String fileName;
    private String fileNameHP;

    private HistoricalProductionService historicalProductionService;
    private TimePeriodService timePeriodService;
    private BOMService bomService;

    private List<String[]> listHP;

    public HistoricalProductionParser(HistoricalProductionService historicalProductionService,
                                      TimePeriodService timePeriodService, BOMService bomService){

        this.historicalProductionService = historicalProductionService;
        this.timePeriodService = timePeriodService;
        this.bomService = bomService;

        list = new ArrayList<>();
        listHP = new ArrayList<>();
    }


    public void parsePeruiod() {
        for(String[] period : list) {
            TimePeriod timePeriod = new TimePeriod();
            try {
                timePeriod.setName(getDate(period[1]));
                timePeriod.setStart(getDate(period[2]));
                timePeriod.setEnd(getDate(period[3]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            timePeriodService.save(timePeriod);
        }
    }

    public void parseBom() {
        for(String[] boms: list) {
            BOM bom = new BOM();
            bom.setName(boms[1]);
            bomService.save(bom, boms[2]);
        }
    }



    public void parse() throws ParseException {
        List<String> dates = new ArrayList<>();
        dates.add("01.07.2020");
        dates.add("01.08.2020");
        dates.add("01.09.2020");
        dates.add("01.10.2020");
        dates.add("01.11.2020");
        dates.add("01.12.2020");
        dates.add("01.01.2021");
        dates.add("01.02.2021");
        dates.add("01.03.2021");
        dates.add("01.04.2021");
        dates.add("01.05.2021");
        dates.add("01.06.2021");

        for(String[] hp : listHP) {

            int  numDates = 0;

            if(hp[6].equals("Пара колесная СОНК")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    }
                    historicalProductionService.save(historicalProduction, "Пара колесная СОНК",
                            hp[2], hp[5], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[5].equals("Пара колесная СОНК РВ2Ш")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "Пара колесная СОНК РВ2Ш",
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[6].equals("Пара колесная НОНД РД")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    }
                    historicalProductionService.save(historicalProduction, "Пара колесная НОНК РД",
                            hp[2], hp[6], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[6].equals("Пара колесная НОНД ОД")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    }
                    historicalProductionService.save(historicalProduction, "Пара колесная НОНК ОД",
                            hp[3], hp[6], getDate(dates.get(numDates)));

                    numDates++;
                }
            }

            if(hp[5].equals("Пара колесная НОСК")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "НОСК_ОД пара колесная обод "+hp[6],
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[5].equals("Пара колесная СОСК")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "СОСК_ОД пара колесная обод "+ hp[6],
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[5].equals("Пара колесная ОД")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "БУ_ОД пара колесная обод "+hp[6],
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[5].equals("Рама боковая ОД")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "БУ_ОД рама боковая срок эксплуатации "+ hp[6],
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
            if(hp[5].equals("Балка надрессорная ОД")) {
                for(int i = 13; i<= 24; i++) {
                    HistoricalProduction historicalProduction = new HistoricalProduction();
                    if (!hp[i].equals("")) {
                        historicalProduction.setCountProduct(Integer.parseInt(hp[i]));
                    } else {
                        historicalProduction.setCountProduct(0);
                    }
                    historicalProductionService.save(historicalProduction, "БУ_ОД балка надрессорная срок эксплуатации "+ hp[6],
                            hp[3], hp[4], getDate(dates.get(numDates)));

                    numDates++;
                }
            }
//            if(hp[6].equals("Пара колёсная НОСК")){
//                HistoricalProduction historicalProduction = new HistoricalProduction();
//                historicalProduction.setCountProduct(Integer.parseInt(hp[7]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОСК",
//                        hp[2], hp[6], getDate("01.01.2020"));
//                historicalProduction.setCountProduct(Integer.parseInt(hp[8]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОНК РД",
//                        hp[2], hp[6], getDate("01.02.2020"));
//                historicalProduction.setCountProduct(Integer.parseInt(hp[9]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОНК РД",
//                        hp[2], hp[6], getDate("01.03.2020"));
//                historicalProduction.setCountProduct(Integer.parseInt(hp[10]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОНК РД",
//                        hp[2], hp[6], getDate("01.04.2020"));
//                historicalProduction.setCountProduct(Integer.parseInt(hp[11]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОНК РД",
//                        hp[2], hp[6], getDate("01.05.2020"));
//                historicalProduction.setCountProduct(Integer.parseInt(hp[12]));
//                historicalProductionService.save(historicalProduction, "Пара колёсная НОНК РД",
//                        hp[2], hp[6], getDate("01.06.2020"));
//            }
        }
    }

    private Date getDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(date);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void updateBOM(){
        historicalProductionService.updateAllBOM();
    }

    public void initPrevData() {
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
    public void initPrevDataHP() {
        Reader reader = null;
        try {
            try {
                reader = new InputStreamReader(new FileInputStream(this.fileNameHP), "cp1251");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVReader csvReader = new CSVReader(reader);
        try {
            listHP = csvReader.readAll();
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

        listHP.remove(0);
        listHP.remove(0);

    }

    public void setFileNameHP(String fileNameHP) {
        this.fileNameHP = fileNameHP;
    }
}
