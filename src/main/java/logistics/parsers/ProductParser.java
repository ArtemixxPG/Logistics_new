package logistics.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.entityes.Products;
import logistics.services.ProductService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductParser {

    private ProductService productService;

    private List<String[]> list;

    private String fileName;



    public  ProductParser(ProductService productService){
        this.productService = productService;
        this.list = new ArrayList<>();


    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void parser(){
        for(String[] product: list){
            Products products = new Products();
            products.setName(product[1]);
            productService.save(products);
        }
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
