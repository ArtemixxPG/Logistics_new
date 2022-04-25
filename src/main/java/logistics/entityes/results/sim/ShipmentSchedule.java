package logistics.entityes.results.sim;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shipment_schedule")
@Data
public class ShipmentSchedule implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "statistics")
    private String statistics;

    @Column(name = "object")
    private String object;

    @Column(name = "shipment_id")
    private String shipment_id;

    @Column(name = "original_shipper")
    private String original_shipper;

    @Column(name = "vehicle_type")
    private String vehicle_type;

    @Column(name = "vehicle_amount")
    private Integer vehicle_amount;

    @Column(name = "previous_location")
    private String previous_location;

    @Column(name = "current_location")
    private String current_location;

    @Column(name = "next_location")
    private String next_location;

    @Column(name = "action")
    private String action;

    @Column(name = "date")
    private String date;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "unit")
    private String unit;


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
