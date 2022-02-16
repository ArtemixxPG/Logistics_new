package logistics.entityes;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "other_cost")
public class OtherCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "iteration")
    private Long iteration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @Column(name = "other_cost_per_day")
    private Long other_cost_per_day;

    @Column(name = "other_cost_per_period")
    private Long other_cost_per_period;



}
