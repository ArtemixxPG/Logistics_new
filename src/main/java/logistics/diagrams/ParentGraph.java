package logistics.diagrams;

import com.fasterxml.jackson.annotation.JsonView;
import logistics.view.View;
import lombok.Data;

import java.util.List;

@Data
public abstract class ParentGraph {



    @JsonView(View.Public.class)
    protected List<Double> data;
    @JsonView(View.Public.class)
    protected String label;
    @JsonView(View.Public.class)
    protected String borderColor;
    @JsonView(View.Public.class)
    protected String backgroundColor;
    @JsonView(View.Public.class)
    protected boolean fill;


}
