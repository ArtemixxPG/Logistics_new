package logistics.controllers.result.sim;

import logistics.entityes.results.sim.ProductFlows;
import logistics.entityes.results.sim.ShipmentSchedule;
import logistics.services.results.sim.SIMResultService;
import logistics.services.results.sim.SIMResultServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sim")
public class ProductFlowsController {

    private SIMResultService sIMResultService;

    public ProductFlowsController(SIMResultService sIMResultService){
        this.sIMResultService = sIMResultService;
    }

    @GetMapping("/pf")
    @CrossOrigin
    public List<ProductFlows> getProductFlows(){
        return sIMResultService.getProductFlows_s();
    }

    @GetMapping("/ss")
    @CrossOrigin
    public List<ShipmentSchedule> getShipmentSchedules(){
        return sIMResultService.getShipmentSchedules();
    }

    @GetMapping("/pf/data")
    @CrossOrigin
    public SIMResultServiceImpl.ResultData getResultPFData() throws CloneNotSupportedException{
        return  sIMResultService.getResultDataPF();
    }

    @GetMapping("/ss/data")
    @CrossOrigin
    public SIMResultServiceImpl.ResultData getResultSSData() throws CloneNotSupportedException{
        return  sIMResultService.getResultDataSS();
    }
}
