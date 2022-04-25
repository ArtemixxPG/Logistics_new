package logistics.services.results.sim;

import logistics.entityes.results.sim.ProductFlows;
import logistics.entityes.results.sim.ShipmentSchedule;
import logistics.entityes.results.sim.TotalCost;

import java.util.List;

public interface SIMResultService {



    void saveShipmentSchedule(ShipmentSchedule shipmentSchedule);
    void saveProductFlows(ProductFlows productFlows);

    void saveTotalCost(TotalCost totalCost);

    List<ProductFlows> getProductFlows_s();
    List<ShipmentSchedule> getShipmentSchedules();
    List<TotalCost> getTotalCosts();
    SIMResultServiceImpl.ResultData getResultDataPF() throws CloneNotSupportedException;
    SIMResultServiceImpl.ResultData getResultDataSS() throws CloneNotSupportedException;



}
