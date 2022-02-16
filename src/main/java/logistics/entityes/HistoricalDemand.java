package logistics.entityes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historical_demand")
public class HistoricalDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "count")
    private Integer count;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "price")
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_name", referencedColumnName = "name")
    private Products products;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_name", referencedColumnName = "name")
    private Customers customers;

    public HistoricalDemand(){}


    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Integer getCount() {
        return count;
    }

    public Long getPrice() {
        return price;
    }

    public Products getProducts() {
        return products;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setProducts(Products products) {
        this.products = products;
    }


}
