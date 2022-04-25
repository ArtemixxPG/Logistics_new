package logistics.entityes.results.sim;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "total_cost")
public class TotalCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "statistics")
    private String statistics;

    @Column(name = "type")
    private String type;

    @Column(name = "object")
    private String object;

    @Column(name = "product")
    private String product;

    @Column(name = "vehicle_type")
    private String vehicle_type;;

    @Column(name = "source")
    private String source;

    @Column(name = "sale_bath")
    private String sale_bath;

    @Column(name = "bom")
    private String bom;

    @Column(name = "period")
    private String period;

    @Column(name = "account")
    private String account;

    @Column(name = "destination")
    private String destination;

    @Column(name = "zone")
    private String zone;

    @Column(name = "staff_type")
    private String staff_type;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;

}
