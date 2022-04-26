package logistics.entityes;

import com.opencsv.bean.CsvBindByName;
import logistics.entityes.HistoricalDemand;
import logistics.entityes.Icon;
import logistics.entityes.Locations;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customers")
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

    public HistoricalDemand getHistoricalDemand() {
        return historicalDemand;
    }

    public void setHistoricalDemand(HistoricalDemand historicalDemand) {
        this.historicalDemand = historicalDemand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getInclusion() {
        return inclusion;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public Locations getLocation() {
        return locations;
    }

    public void setLocation(Locations locations) {
        this.locations = locations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
