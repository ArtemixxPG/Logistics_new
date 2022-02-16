package logistics.entityes;


import com.fasterxml.jackson.annotation.JsonView;
import logistics.view.View;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "site_state")
public class SiteState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Public.class)
    private Long id;

    @Column(name = "iteration")
    @JsonView(View.Public.class)
    private Long iteration;


    @Column(name = "period")
    @JsonView(View.Public.class)
    private String timePeriod;

    @Column(name = "site")
    @JsonView(View.Public.class)
    private String site;

    @Column(name = "initial_state")
    @JsonView(View.Public.class)
    private String initial_state;

    @Column(name = "new_state")
    @JsonView(View.Public.class)
    private String new_state;

    @Column(name = "initial_cost")
    @JsonView(View.Public.class)
    private Long initial_cost;

    @Column(name = "closing_cost")
    @JsonView(View.Public.class)
    private Long closing_cost;


}
