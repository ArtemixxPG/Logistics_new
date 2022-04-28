package logistics.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import logistics.entityes.Locations;
import logistics.services.LocationService;
import logistics.services.LocationServiceImpl;
import logistics.view.View;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping
    public Long save(@RequestBody Locations locations){
        return locationService.save(locations);
    }

    @GetMapping
    @CrossOrigin
    @JsonView(View.Public.class)
    public List<Locations> getAll(){
       return locationService.getAll();
    }
}
