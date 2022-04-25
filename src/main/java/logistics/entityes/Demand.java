package logistics.entityes;


import com.opencsv.bean.CsvBindByName;

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
    @CsvBindByName(column = "Revenue")
    private Long revenue;

    @Column(name = "currency")
    @CsvBindByName(column = "")
    private String currency;

    @Transient
    @CsvBindByName(column = "Customers")
    private String customersName;

    @Transient
    @CsvBindByName(column = "Product")
    private String productName;

    public Demand(){}

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setId_demand(String id_demand) {
        this.id_demand = id_demand;
    }

    public String getId_demand() {
        return id_demand;
    }
}
