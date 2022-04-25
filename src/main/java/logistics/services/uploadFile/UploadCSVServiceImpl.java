package logistics.services.uploadFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import logistics.DAO.main.TimePeriodDAO;
import logistics.entityes.TimePeriod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
@Transactional
public class UploadCSVServiceImpl implements UploadCSVService{


    private TimePeriodDAO timePeriodDAO;


    public UploadCSVServiceImpl(TimePeriodDAO timePeriodDAO) {
        this.timePeriodDAO = timePeriodDAO;
    }


    @Override
    public void upload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("Please select a CSV file to upload.");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<TimePeriod> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(TimePeriod.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<TimePeriod> timePeriods = csvToBean.parse();

                timePeriodDAO.saveAll(timePeriods);

            } catch (IOException e) {
                System.out.println("An error occurred while processing the CSV file.");
                e.printStackTrace();
            }
        }
        }


}
