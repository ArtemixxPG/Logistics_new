package logistics.entityes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "period")
public class TimePeriod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Temporal(TemporalType.TIMESTAMP)
    private Date name;

    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "ending")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ending;

    @Column(name = "demand")
    private Double demand;

    @Column(name = "coefficient")
    private Double coefficient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timePeriod")
    private List<StorageByProduct> storageByProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timePeriod")
    private List<OperationsSite> operatingSites;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timePeriod")
    private List<ProductionCost> productionCosts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timePeriod")
    private List<ProductionFlow> productionFlows;







    public TimePeriod(){}

    public Long getId() {
        return id;
    }


    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return ending;
    }

    public void setEnd(Date end) {
        this.ending = end;
    }

    public Double getDemand() {
        return demand;
    }

    public void setDemand(Double demand) {
        this.demand = demand;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public List<StorageByProduct> getStorageByProducts() {
        return storageByProducts;
    }

    public void setStorageByProducts(List<StorageByProduct> storageByProducts) {
        this.storageByProducts = storageByProducts;
    }
}
