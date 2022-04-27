package logistics.entityes;

import com.opencsv.bean.CsvBindByName;
import logistics.entityes.HistoricalDemand;
import logistics.entityes.Icon;
import logistics.entityes.Locations;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;

    @Column(name = "type")
    @CsvBindByName(column = "type")
    private String type;

    @Column(name = "inclusion")
    @CsvBindByName(column = "inclusion")
    private String inclusion;

    @Transient
    @CsvBindByName(column = "icon")
    private String iconName;

    @Transient
    @CsvBindByName(column = "locations")
    private String locationName;

    @Transient
    @CsvBindByName(column = "Historical Demand")
    private String historicalDemandName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "icon", referencedColumnName = "id")
    private Icon icon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "name")
    private Locations locations;

    @OneToOne(cascade = CascadeType.ALL)
    private HistoricalDemand historicalDemand;

    @OneToOne(cascade = CascadeType.ALL)
    private Sale sale;

    public Customers(){}


}
