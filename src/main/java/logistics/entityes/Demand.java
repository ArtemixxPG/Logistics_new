package logistics.entityes;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "demand")
public class Demand {

    @Id
//    @GenericGenerator(name = "demand-generator",
//    parameters = @Parameter(name = "prefix", value = "demand"),
//    strategy = "logistics.keys.GeneratedDemandKey")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "name")
    private Customers customers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;

    @Column(name = "id_demand")
    private String id_demand;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "currency")
    private String curency;

    public Demand(){}

    public Long getId() {
        return id;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public String getCurency() {
        return curency;
    }

    public void setCurency(String curency) {
        this.curency = curency;
    }

    public void setId_demand(String id_demand) {
        this.id_demand = id_demand;
    }

    public String getId_demand() {
        return id_demand;
    }
}
