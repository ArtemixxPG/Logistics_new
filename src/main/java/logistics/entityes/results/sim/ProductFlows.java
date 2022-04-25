package logistics.entityes.results.sim;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "product_flows")
public class ProductFlows implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "statistics")
    private String statistics;

    @Column(name = "object")
    private String object;

    @Column(name = "product")
    private String product;

    @Column(name = "destination")
    private String destination;

    @Column(name = "period")
    private String period;

    @Column(name = "amount")
    private Double amount;


    public ProductFlows() {

    }

    public ProductFlows(ProductFlows productFlows) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
