package logistics.entityes;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "operations_site")
public class OperationsSite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "Iteration")
    private Long iteration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @Column(name = "status")
    private String status;

    @Column(name = "initial_cost")
    private Double initialCost;

    @Column(name = "closing_cost")
    private Double closing_cost;

    @Column(name = "other_cost_per_time")
    private Double other_cost_per_time;

    @Column(name = "total_cost")
    private Double totalCost;

}
