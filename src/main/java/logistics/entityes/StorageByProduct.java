package logistics.entityes;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "storage_by_product")

public class StorageByProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name ="itaration")
    private Long itaration;

    @Column(name = "in_flow")
    private Long in_flow;

    @Column(name = "initial_storage")
    private Long initial_storage;

    @Column(name = "storage")
    private Long storage;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "out_flow")
    private Long out_flow;

    @Column(name = "carrying_cost_per_unit")
    private Long carrying_cost_per_unit;

    @Column(name = "carrying_cost_total")
    private Long carrying_cost_total;

    @Column(name = "storage_min")
    private Long storage_min;

    @Column(name = "storage_max")
    private Long storage_max;

    @Column(name = "understock_penalty_per_unit")
    private Long understock_penalty_per_unit;

    @Column(name = "overstock_penalty_per_unit")
    private Long overstock_penalty_per_unit;

    @Column(name = "storage_penalty")
    private Long storage_penalty;

    @Column(name = "total_cost")
    private Double total_cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;
}
