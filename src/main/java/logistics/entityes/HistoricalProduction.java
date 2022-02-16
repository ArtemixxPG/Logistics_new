package logistics.entityes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "historical_production")
public class HistoricalProduction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "countProduct")
    private Integer countProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "site", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOM", referencedColumnName = "name")
    private BOM bom;



    public HistoricalProduction(){}

    public Long getId() {
        return id;
    }

    public Products getProducts() {
        return products;
    }

    public DCsAndFactories getdCsAndFactories() {
        return dCsAndFactories;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public void setdCsAndFactories(DCsAndFactories dCsAndFactories) {
        this.dCsAndFactories = dCsAndFactories;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }

    public BOM getBom() {
        return bom;
    }

    public void setBom(BOM bom) {
        this.bom = bom;
    }
}
