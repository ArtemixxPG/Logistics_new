package logistics.parsers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParserCSV {
    private static final String csvExtension = "csv";

    public static void CustomersToCsv(Writer writer, List<Object> objectsToCSV, String... headers) throws IOException {

        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader(headers).build();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {


                csvPrinter.printRecord(objectsToCSV);

            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static List<String[]> parseCsvFile(InputStream is, String... headers) {
        BufferedReader br = null;
        CSVParser parserCSV = null;

        List<String[]> inputs = new ArrayList<>();

        try {
            CSVFormat format =  CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true).build();
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            parserCSV = new CSVParser(br, format);

            Iterable<CSVRecord> csvRecords = parserCSV.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String[] input = new String[headers.length];
                for(int i = 0; i < input.length; i++) {
                    input[i] = csvRecord.get(headers[i]);
                }
                inputs.add(input);
            }
        } catch (Exception e) {
            System.out.println("Reading CSV File");
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
                assert parserCSV != null;
                parserCSV.close();
            } catch (IOException e) {
                System.out.println("Closing file error");
                e.printStackTrace();
            }
        }
        return inputs;
    }

    public static boolean isCSVFile(MultipartFile file) {
        String extension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        return extension.equals(csvExtension);
    }
}
