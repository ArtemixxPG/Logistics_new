package logistics.entityes;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;

@Entity
@Table(name = "demand_for_period")
public class DemandForAllPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "count")
    @CsvBindByName(column = "Count")
    private Integer count;

    @Transient
    @CsvBindByName(column = "Customers")
    private String customerName;

    @Transient
    @CsvBindByName(column = "Period")
    private String periodName;

    @Transient
    @CsvBindByName(column = "Product")
    private String productName;

    @OneToOne
    @JoinColumn(name = "cust_name", referencedColumnName = "name")
    private Customers customers;

    @OneToOne
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @OneToOne
    @JoinColumn(name ="product", referencedColumnName = "name")
    private Products products;

    public DemandForAllPeriod(){}

    public Long getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }
}
