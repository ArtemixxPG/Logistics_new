package logistics.parsers;

import logistics.entityes.results.sim.ProductFlows;
import logistics.entityes.results.sim.ShipmentSchedule;
import logistics.entityes.results.sim.TotalCost;
import logistics.initialization.InitCSV;
import logistics.initialization.InitTXT;
import logistics.services.results.sim.SIMResultService;
import org.springframework.stereotype.Component;

@Component
public class SIMparser {

    private InitCSV initCSV;
    private InitTXT initTXT;

    private String fileName;

    private SIMResultService sIMResultService;

    private SIMparser(SIMResultService sIMResultService) {
        this.sIMResultService = sIMResultService;
    }

    public void parseTotalCost() {
        initCSV = InitCSV.init(fileName);
        for(String[] line:initCSV.getList()) {
            TotalCost cost = new TotalCost();
            cost.setStatistics(line[0]);
            cost.setType(line[1]);
            cost.setObject(line[2]);
            cost.setProduct(line[3]);
            cost.setVehicle_type(line[4]);
            cost.setSource(line[5]);
            cost.setSale_bath(line[6]);
            cost.setBom(line[7]);
            cost.setPeriod(line[8]);
            cost.setAccount(line[9]);
            cost.setDestination(line[10]);
            cost.setZone(line[11]);
            cost.setStaff_type(line[12]);
            cost.setUnit(line[14]);
            if(line[13].equals("0")||!Character.isDigit(line[13].charAt(line[13].length()-1))) {
                Double value = Double.parseDouble("0");
                cost.setValue(value);
            } else {
                String prev_value[] = line[13].split(",");
                StringBuilder parserValue = new StringBuilder(prev_value[0]);
                for(int i = 1; i < prev_value.length;i++){
                    parserValue.append(prev_value[i]);
                }

                Double value = Double.parseDouble(parserValue.toString());
                cost.setValue(value);
            }
            sIMResultService.saveTotalCost(cost);
        }
    }

    public void parseShipmentShedule() {
        initCSV = InitCSV.init(fileName);
        for(String[] line:initCSV.getList()) {
            ShipmentSchedule ss = new ShipmentSchedule();
            ss.setStatistics(line[0]);
            ss.setObject(line[1]);
            ss.setShipment_id(line[2]);
            ss.setOriginal_shipper(line[3]);
            ss.setVehicle_type(line[4]);
            ss.setVehicle_amount(Integer.parseInt(line[5]));
            ss.setPrevious_location(line[6]);
            ss.setCurrent_location(line[7]);
            ss.setNext_location(line[8]);
            ss.setAction(line[9]);
            ss.setDate(line[10]);
            ss.setProduct(line[11]);
            ss.setUnit(line[13]);
            if(line[12].equals("-")||!Character.isDigit(line[12].charAt(0))
                    ||!Character.isDigit(line[12].charAt(line[12].length() - 1))){
                Double value = Double.parseDouble("0");
                ss.setQuantity(value);
            } else {
                if((line[12].split(",").length>=2)) {
                    Double value = Double.parseDouble(line[12].split(",")[0]+line[12].split(",")[1]);
                    ss.setQuantity(value);
                } else if((line[12].split(",").length<2)) {
                    Double value = Double.parseDouble(line[12]);
                    ss.setQuantity(value);
                }

            }
            sIMResultService.saveShipmentSchedule(ss);
        }
    }

    public void parseProductFlows(){
        initCSV = InitCSV.init(fileName);
        for(String[] line : initCSV.getList()){
            ProductFlows productFlows = new ProductFlows();
            productFlows.setStatistics(line[0]);
            productFlows.setObject(line[1]);
            productFlows.setProduct(line[2]);
            productFlows.setDestination(line[3]);
            productFlows.setPeriod(line[4]);
            if(line[5].equals("0")){
                Double value = Double.parseDouble(line[5]);
                productFlows.setAmount(value);
            } else {
                String prev_value[] = line[5].split(",");
                StringBuilder parserValue = new StringBuilder(prev_value[0]);
                for(int i = 1; i < prev_value.length;i++){
                    parserValue.append(prev_value[i]);
                }
                Double value = Double.parseDouble(parserValue.toString());
                productFlows.setAmount(value);
            }
            sIMResultService.saveProductFlows(productFlows);
        }
    }

    public void parseProductFlowsFromTXT() {
        initTXT = InitTXT.init(fileName);
        for(String[] line:initTXT.getList()) {
            ProductFlows productFlows = new ProductFlows();
            productFlows.setStatistics(line[0]);
            productFlows.setObject(line[1]);
            productFlows.setProduct(line[2]);
            productFlows.setDestination(line[3]);
            productFlows.setPeriod(line[4]);
            if(line[5].equals("0")){
                Double value = Double.parseDouble(line[5]);
                productFlows.setAmount(value);
            } else {
                String prev_value[] = line[5].split(",");
                StringBuilder parserValue = new StringBuilder(prev_value[0]);
                for(int i = 1; i < prev_value.length;i++){
                    parserValue.append(prev_value[i]);
                }
                Double value = Double.parseDouble(parserValue.toString());
                productFlows.setAmount(value);
            }
            sIMResultService.saveProductFlows(productFlows);
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
