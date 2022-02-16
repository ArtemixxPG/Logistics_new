package logistics.entityes;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "production_cost")
public class ProductionCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iteration")
    private Long iteration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bom", referencedColumnName = "name")
    private BOM bom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_production", referencedColumnName = "name")
    private Products products;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "production_cost")
    private Double productionCost;

    @Column(name = "min_throughput")
    private Double minThroughput;

    @Column(name = "max_throughput")
    private Double maxThroughput;

    @Column(name = "production_penalty")
    private Double production_penalty;

    @Column(name = "total_cost")
    private Double total_cost;

}
