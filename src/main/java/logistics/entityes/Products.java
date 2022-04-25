package logistics.entityes;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "products")
public class Products implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @CsvBindByName(column = "Name")
    private String name;

    @Column(name = "unit")
    @CsvBindByName(column = "Unit")
    private String unit;


    @OneToOne(cascade = CascadeType.ALL)
    private HistoricalDemand historicalDemand;


    @OneToOne(cascade = CascadeType.ALL)
    private Sale sale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<StorageByProduct> storageByProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<ProductionCost> productionCosts;



    public Products(){
    }

    public HistoricalDemand getHistoricalDemand() {
        return historicalDemand;
    }

    public void setHistoricalDemand(HistoricalDemand historicalDemand) {
        this.historicalDemand = historicalDemand;
    }

    public Long getId() {
        return id;
    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
