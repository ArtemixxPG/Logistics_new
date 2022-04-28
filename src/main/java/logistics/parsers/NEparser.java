package logistics.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.entityes.SiteState;
import logistics.entityes.results.NamedExpression;
import logistics.services.results.NEService;
import logistics.services.results.SiteStateService;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NEparser {

    private List<String[]> list;
    private String filename;
    private NEService neService;
    private SiteStateService siteStateService;

    public NEparser(NEService neService, SiteStateService siteStateService) {
        list = new ArrayList<>();
        this.neService = neService;
        this.siteStateService = siteStateService;
    }


    public void parser() {
        for(String line[] :list) {
            NamedExpression namedExpression = new NamedExpression();
            namedExpression.setIteration(Integer.parseInt(line[0]));
            namedExpression.setExpression_name(line[1]);
            if(line[2].split(",").length == 2) {
                namedExpression.setValue(Double.parseDouble(line[2].split(",")[0] + "." + line[2].split(",")[1]));
            } else{
                namedExpression.setValue(Double.parseDouble(line[2]));
            }
            neService.save(namedExpression);
        }
    }

    public void parserSS() {
        for(String line[] : list) {
            SiteState siteState = new SiteState();
            siteState.setIteration(Long.parseLong(line[0]));
            siteState.setTimePeriod(line[1]);
            siteState.setSite(line[2]);
            siteState.setInitial_state(line[3]);
            siteState.setNew_state(line[4]);
            siteState.setInitial_cost(Long.parseLong(line[5]));
            siteState.setClosing_cost(Long.parseLong(line[6]));
            siteStateService.save(siteState);
        }
    }

    public void initPrevData() {
        Reader reader = null;
        try {
            try {
                reader = new InputStreamReader(new FileInputStream(this.filename), "cp1251");
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
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);


    }

    public void setFilename(String filename){
        this.filename = filename;
    }
}
