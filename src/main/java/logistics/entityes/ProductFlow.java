package logistics.entityes;




import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "product_flow")
public class ProductFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iteration")
    private Integer iteration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod period;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "From", referencedColumnName = "name")
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "To", referencedColumnName = "name")
    private Suppliers suppliers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_period", referencedColumnName = "name")
    private TimePeriod arrival_period;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;

    @Column(name = "flow")
    private Long flow;

    @Column(name = "unit")
    private String unit;

    @Column(name = "flow_min")
    private Long flow_min;

    @Column(name = "flow_max")
    private Long flow_max;

    @Column(name = "percentage")
    private Long percentage;

    @Column(name = "flow_under")
    private Long flow_under;

    @Column(name = "flow_over")
    private Long flow_over;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "vehicle_type")
    private String vehicle_type;

    @Column(name = "travel_time")
    private Double travel_time;

    @Column(name = "out_processing_cost_per_item")
    private Double out_processing_cost_per_item;

    @Column(name = "out_processing_cost_total")
    private Double out_processing_cost_total;

    @Column(name = "transportatuion_cost_per_item")
    private Double tariff_per_item;

    @Column(name = "transportatuion_cost_total")
    private Double tariff_total;

    @Column(name = "in_processing_cost_per_item")
    private Double in_processing_cost_per_item;

    @Column(name = "in_processing_cost_total")
    private Double in_processing_cost_total;

    @Column(name = "flow_cost_per_item")
    private Double flow_cost_per_item;

    @Column(name = "low_cost_total")
    private Double flow_cost_total;

    @Column(name = "penalty")
    private Double penalty;

    @Column(name = "flow_co2_per_item")
    private Double flow_co2_per_item;

    @Column(name = "flow_co2_total")
    private Double flow_co2_total;

    @Column(name = "total")
    private Double total;


}
