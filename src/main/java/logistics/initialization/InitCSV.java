package logistics.initialization;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sun.org.apache.xml.internal.security.Init;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InitCSV {

    private static volatile InitCSV instance;

    private List<String[]> list;
    private String fileName;


    private InitCSV(String fileName) {
        list = new ArrayList<>();
        this.fileName = fileName;
        initPrevData();
    }


    private void initPrevData() {
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

    public static InitCSV init(String fileName) {
        InitCSV result = instance;
        if (result != null) {
            return result;
        }
        synchronized (InitCSV.class) {
            if (instance == null) {
                instance = new InitCSV(fileName);
            }
            return instance;
        }

    }

    public List<String[]> getList() {
        return list;
    }
}
