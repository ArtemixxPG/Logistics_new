package logistics.entityes;

import com.opencsv.bean.CsvBindByName;
import logistics.entityes.Locations;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dcs_and_factories")
@Data
public class DCsAndFactories implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @CsvBindByName(column = "Name")
    private String name;

    @Column(name = "type")
    @CsvBindByName(column = "Type")
    private String type;

    @Column(name = "initially_open")
    @CsvBindByName(column = "Initially Open")
    private Double initially_open;

    @Column(name = "capacity")
    @CsvBindByName(column = "Capacity")
    private Double capacity;

    @Column(name = "capacity_unit")
    @CsvBindByName(column = "Capacity Unit")
    private String capacity_unit;

    @Column(name = "aggregate_orders_by_location")
    @CsvBindByName(column = "Aggregate Orders By Location")
    private Double aggOrByLoc;

    @Transient
    @CsvBindByName(column = "locations")
    private String locationName;

    @Transient
    @CsvBindByName(column = "Type")
    private String typeName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "name")
    private Locations locations;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dCsAndFactories")
    private List<StorageByProduct> storageByProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dCsAndFactories")
    private List<OperationsSite> operatingSites;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dCsAndFactories")
    private List<ProductionCost> productionCosts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dCsAndFactories")
    private List<ProductionFlow> productionFlows;




//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "icon", columnDefinition = "id")
//    private Icon icon;

    public DCsAndFactories() {}

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

    public String getType() {
        return type;
    }

    public Locations getLocation() {
        return locations;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(Locations locations) {
        this.locations = locations;
    }

//    public void setIcon(Icon icon) {
//        this.icon = icon;
//    }
//
//    public Icon getIcon() {
//        return icon;
//    }

    public Double getAggOrByLoc() {
        return aggOrByLoc;
    }

    public void setAggOrByLoc(Double aggOrByLoc) {
        this.aggOrByLoc = aggOrByLoc;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getInitially_open() {
        return initially_open;
    }

    public void setInitially_open(Double initially_open) {
        this.initially_open = initially_open;
    }

    public String getCapacity_unit() {
        return capacity_unit;
    }

    public void setCapacity_unit(String capacity_unit) {
        this.capacity_unit = capacity_unit;
    }


}

