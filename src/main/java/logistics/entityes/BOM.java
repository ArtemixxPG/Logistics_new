package logistics.entityes;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BOM")
public class BOM implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @CsvBindByName(column = "name")
    private String name;

    @Transient
    @CsvBindByName(column = "Product")
    private String productName;

    @OneToOne
    @JoinColumn(name = "end_product", referencedColumnName = "name")
    private Products products;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bom")
    private List<ProductionCost> productionCosts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bom")
    private List<ProductionFlow> productionFlows;

    public BOM(){}

    public Long getId() {
        return id;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
