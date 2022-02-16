package logistics.controllers.result;

import logistics.entityes.SiteState;
import logistics.services.results.SiteStateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ss")
public class SiteStateController {

    private SiteStateService siteStateService;

    public SiteStateController(SiteStateService siteStateService) {
        this.siteStateService = siteStateService;
    }

    @PostMapping
    @CrossOrigin
    public Long save(@RequestBody SiteState siteState){
        return siteStateService.save(siteState);
    }

    @GetMapping
    @CrossOrigin
    public List<SiteState> getAll() {
        return siteStateService.getAll();
    }
}
