package logistics.services.results.sim;

import logistics.DAO.result.sim.ProductFlowsDAO;
import logistics.DAO.result.sim.ShipmentScheduleDAO;
import logistics.DAO.result.sim.TotalCostDAO;
import logistics.entityes.results.sim.ProductFlows;
import logistics.entityes.results.sim.ShipmentSchedule;
import logistics.entityes.results.sim.TotalCost;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class SIMResultServiceImpl implements SIMResultService {


    private ResultData resultData;
    private ShipmentScheduleDAO shipmentScheduleDAO;
    private ProductFlowsDAO productFlowsDAO;
    private TotalCostDAO totalCostDAO;
    private double firstValue, secondValue;

    public SIMResultServiceImpl(ShipmentScheduleDAO shipmentScheduleDAO,
                                ProductFlowsDAO productFlowsDAO,
                                TotalCostDAO totalCostDAO) {
        this.shipmentScheduleDAO = shipmentScheduleDAO;
        this.productFlowsDAO = productFlowsDAO;
        this.totalCostDAO = totalCostDAO;
    }

    @Override
    public void saveShipmentSchedule(ShipmentSchedule shipmentSchedule) {
        shipmentScheduleDAO.save(shipmentSchedule);
    }

    @Override
    public void saveProductFlows(ProductFlows productFlows) {
        productFlowsDAO.save(productFlows);
    }

    @Override
    public void saveTotalCost(TotalCost totalCost) {
        totalCostDAO.save(totalCost);
    }

    @Override
    public List<ProductFlows> getProductFlows_s() {
        return productFlowsDAO.findAll();
    }

    @Override
    public List<ShipmentSchedule> getShipmentSchedules() {
        return shipmentScheduleDAO.findAll();
    }

    @Override
    public List<TotalCost> getTotalCosts() {
        return totalCostDAO.findAll();
    }

    @Override
    public ResultData getResultDataPF() throws CloneNotSupportedException {
         resultData = new ResultData<ProductFlows>();

         List<ProductFlows> productFlows = productFlowsDAO.findAll();
         resultData.setDataTable(productFlows);


        List<ProductFlows> productFlowsForChart = new ArrayList<>();
        for (ProductFlows productFlow : productFlows) {
            Object clone = productFlow.clone();
            productFlowsForChart.add((ProductFlows) clone);
        }

         List<ResultData.DataSet> dataSet = new ArrayList<>();

         while(productFlowsForChart.size() > 0) {
             String datetime = productFlowsForChart.get(0).getPeriod();

             List<ProductFlows> productFlowsWithCurrentPeriod = productFlowsForChart.stream()
                     .filter(pf->(pf.getPeriod().equals(datetime)))
                     .collect(toList());

             double value = 0;

             for(ProductFlows currentProductFlows : productFlowsWithCurrentPeriod) {
                 value += currentProductFlows.getAmount();
             }

             ResultData.DataSet currentDataSet = resultData.new DataSet();

             currentDataSet.setFirstValue(value);
             currentDataSet.setName(datetime.split(" ")[0] + " " + datetime.split(" ")[1].substring(0, datetime.split(" ")[1].length()-1));

             dataSet.add(currentDataSet);

             productFlowsForChart.removeAll(productFlowsWithCurrentPeriod);

         }

         resultData.setDataSet(dataSet);

        return resultData;
    }

    public ResultData getResultDataSS() throws CloneNotSupportedException {
        firstValue = 0;
        secondValue = 0;
        resultData = new ResultData<ShipmentSchedule>();

        List<ShipmentSchedule> shipmentSchedules = shipmentScheduleDAO.findAll();
        resultData.setDataTable(shipmentSchedules);


        List<ShipmentSchedule> shipmentSchedulesForChart = new ArrayList<>();
        for (ShipmentSchedule shipmentSchedule : shipmentSchedules) {
            Object clone = shipmentSchedule.clone();
            shipmentSchedulesForChart.add((ShipmentSchedule) clone);
        }


        List<ResultData.DataSet> dataSet = new ArrayList<>();

        while(shipmentSchedulesForChart.size() > 0) {
            String datetime = shipmentSchedulesForChart.get(0).getDate();

            List<ShipmentSchedule> shipmentSchedulesWithCurrentPeriod = shipmentSchedulesForChart.stream()
                    .filter(ss->(ss.getDate().equals(datetime)))
                    .collect(toList());


            for(ShipmentSchedule shipmentSchedule : shipmentSchedulesWithCurrentPeriod) {
                firstValue += shipmentSchedule.getQuantity();
                secondValue += shipmentSchedule.getVehicle_amount();
            }

            ResultData.DataSet currentDataSet = resultData.new DataSet();

            currentDataSet.setFirstValue(firstValue);
            currentDataSet.setSecondValue(secondValue);
            currentDataSet.setName(datetime);

            dataSet.add(currentDataSet);

            firstValue = 0;
            secondValue = 0;

            shipmentSchedulesForChart.removeAll(shipmentSchedulesWithCurrentPeriod);

        }

        resultData.setDataSet(dataSet);

        return resultData;
    }

    public class ResultData<T> {


        private List<T> dataTable;
        private List<DataSet> dataSet;

        public ResultData() {}

        public List<T> getDataTable() {
            return dataTable;
        }

        public void setDataTable(List<T> dataTable) {
            this.dataTable = dataTable;
        }

        public List<DataSet> getDataSet() {
            return dataSet;
        }

        public void setDataSet(List<DataSet> dataSet) {
            this.dataSet = dataSet;
        }

        public class DataSet {
            private String name;
            private Double firstValue;
            private Double secondValue;

            public DataSet() {}

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getFirstValue() {
                return firstValue;
            }

            public void setFirstValue(Double value) {
                this.firstValue = value;
            }

            public Double getSecondValue() {
                return secondValue;
            }

            public void setSecondValue(Double value) {
                this.secondValue = value;
            }
        }
    }
}
