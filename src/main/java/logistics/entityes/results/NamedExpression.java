package logistics.entityes.results;

import com.fasterxml.jackson.annotation.JsonView;
import logistics.view.View;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "named_expression")
public class NamedExpression {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Public.class)
    private Long id;

    @Column(name = "iteration")
    @JsonView(View.Public.class)
    private Integer iteration;

    @Column(name = "expression_name")
    @JsonView(View.Public.class)
    private String expression_name;

    @Column(name = "value")
    @JsonView(View.Public.class)
    private Double value;
}
