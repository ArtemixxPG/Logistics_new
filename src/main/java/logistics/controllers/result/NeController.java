package logistics.controllers.result;

import com.fasterxml.jackson.annotation.JsonView;
import logistics.diagrams.NEDiagrams;
import logistics.entityes.Locations;
import logistics.entityes.results.NamedExpression;
import logistics.services.results.NEService;
import logistics.view.View;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ne")
public class NeController {

    private NEService neService;

    public NeController(NEService neService) {
        this.neService = neService;
    }

    @PostMapping
    public Long save(@RequestBody NamedExpression namedExpression){
        return neService.save(namedExpression);
    }

    @GetMapping
    @CrossOrigin
    @JsonView(View.Public.class)
    public List<NamedExpression> getByIteration(@RequestParam(name = "iteration")int iteration) {
        return neService.getByIteration(iteration);
    }

    @GetMapping("/diagram")
    @CrossOrigin
    @JsonView(View.Public.class)
    public NEDiagrams getDataset(){
        return neService.getDataset();
    }

    @PutMapping
    @CrossOrigin
    public Long update(@RequestBody NamedExpression namedExpression) {
        return neService.update(namedExpression);
    }

    @DeleteMapping
    @CrossOrigin
    public void delete(@RequestBody NamedExpression namedExp){
         neService.delete(namedExp);
    }

}
