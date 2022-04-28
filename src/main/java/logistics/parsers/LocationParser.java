package logistics.parsers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import logistics.entityes.Locations;
import logistics.services.LocationService;
import logistics.services.LocationServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.Location;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationParser {

    private static final String[] terrObject = {"обл.", "Область", "р-н", "респ.", "Республика", "Респ", "край"};

    private List<Locations> data;

    private String fileName;
    List<String[]> list;

    private LocationService locationService;

    public LocationParser(LocationService locationService){
        this.list = new ArrayList<>();
        this.data = new ArrayList<>();
        this.locationService = locationService;

    }


//    public void initData(){
//        for(String[] str : list){
//            if(str[6].split(",").length <= 6 && str[6].split(",").length > 4) {
//
//                Locations locations = new Locations();
//                locations.setCode(str[6].split(",")[0]);
//               locations.setName(str[0]);
//               locations.setCity(str[6].split(",")[2]);
//               locations.setRegion(str[6].split(",")[1]);
//               locations.setCountry("Россия");
//
//               if(str[6].split(",").length == 6) {
//                   if (findKorpus(str[6].split(",")[5])) {
//                       locations.setAddress(str[6].split(",")[3] + "," + str[6].split(",")[4] + "," +
//                               str[6].split(",")[5]);
//                   }
//               } else {
//                   locations.setAddress(str[6].split(",")[3] + "," + str[6].split(",")[4]);
//               }
//                data.add(locations);
//            }
//
//        }
//    }

    public void initData() {
        for (String[] str : list) {
            if (!str[0].equals("")) {
                Locations locations = new Locations();
                findCode(str, 4, locations);
                locations.setName(str[0]);
                findCity(str, 4, locations);
                findRegion(str, 4, locations);
                locations.setCountry("Россия");
                findAdress(str, 4, locations);

                if(locations.getCode()!=null) {
                    data.add(locations);
                }
            }
            }
    }

    private void findCode(String[] str, int numAdd, Locations locations) {
        String postAddress = str[numAdd];
        String[] parsePostAddress = postAddress.split(",");

        if(parsePostAddress[0].length() == 6) {
            locations.setCode(parsePostAddress[0]);
        } else {
            String[] findCode = parsePostAddress[0].split(" ");
            if(findCode[0].length() == 6 && Character.isDigit(findCode[0].charAt(0))) {
                locations.setCode(findCode[0]);
            }
             else {
                 for(String elem : parsePostAddress) {
                     for (String inElem : elem.split(" ")) {
                         if(inElem.length() == 6 && Character.isDigit(inElem.charAt(0))) {
                             locations.setCode(inElem);
                         }
                     }
                 }
            }
        }
    }

    private void findRegion(String[] str, int numAdd, Locations locations){
        String postAddress = str[numAdd];
        String[] parsePostAddress = postAddress.split(",");

        for(String elem : parsePostAddress) {
            String[] name = elem.split(" ");
            for (int i = 0; i < name.length; i++) {
                for (String terr : terrObject) {
                    if(name[i].equals(terr)){
                        if(name.length == 2){
                            locations.setRegion(elem);
                            break;
                        } else {
                            if (i != 0) {
                                if(name[i - 1].equals("")){
                                    locations.setRegion(name[i] + " " + name[i + 1]);
                                    break;
                                } else {
                                    if (Character.isUpperCase(name[i - 1].charAt(0))) {
                                        locations.setRegion(name[i - 1] + " " + name[i]);
                                        break;
                                    } else if ((i != name.length -1) && (Character.isUpperCase(name[i + 1].charAt(0)))) {
                                        locations.setRegion(name[i] + " " + name[i + 1]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void findCity(String[] str, int numAdd, Locations locations){
        String postAddress = str[numAdd];
        String[] parsePostAddress = postAddress.split(",");

        for(String elem : parsePostAddress) {
            String[] name = elem.split(" ");
            for (int i = 0; i < name.length; i++) {
                    if(name[i].equals("г.") || name[i].equals("г")){
                        if(name.length == 2){
                            locations.setCity(elem);
                            break;
                        } else {
                            if(i != 0) {
                                if(name[i - 1].equals("")){
                                    locations.setCity(name[i] + " " + name[i + 1]);
                                    break;
                                } else {
                                    if (Character.isUpperCase(name[i - 1].charAt(0))) {
                                        locations.setCity(name[i - 1] + " " + name[i]);
                                        break;
                                    } else if ((i != name.length -1) && (Character.isUpperCase(name[i + 1].charAt(0)))) {
                                        locations.setCity(name[i] + " " + name[i + 1]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }



    private void findAdress(String[] str, int numAdd, Locations locations) {
        String postAddress = str[numAdd];
        String[] parsePostAddress = postAddress.split(",");

        for(int e = 0; e < parsePostAddress.length; e++) {
            String[] name = parsePostAddress[e].split(" ");
            for (int i = 0; i < name.length; i++) {
                if(name[i].equals("ул.") || name[i].equals("улица") ||
                        name[i].equals("станция")|| name[i].equals("парк")) {
                    if(name.length == 2){
                        if(e!=parsePostAddress.length - 1) {
                            locations.setAddress(parsePostAddress[e] + parsePostAddress[e + 1]);
                            break;
                        } else {
                            locations.setAddress(parsePostAddress[e]);
                            break;
                        }
                    }
                    else {
                        if (i != 0) {
                            if(name[i - 1].equals("")) {
                                locations.setAddress(name[i] + " " + name[i + 1]);
                                break;
                            } else {
                                if (Character.isUpperCase(name[i - 1].charAt(0))) {
                                    locations.setAddress(name[i - 1] + " " + name[i]);
                                    break;
                                } else if ((i != name.length -1) && (Character.isUpperCase(name[i + 1].charAt(0)))) {
                                    locations.setAddress(name[i] + " " + name[i + 1]);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean findKorpus(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'к'){
                return true;
            }
        }
        return false;
    }

    public void getPostgree() {
        for(Locations locations : data) {
            locationService.save(locations);
        }
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String[]> getList() {
        return list;
    }
}
