package logistics.entityes;

import javax.persistence.*;

@Entity
@Table(name = "cust_and_dc_product")
public class CustAndDCProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer", referencedColumnName = "name")
    private Customers customers;

    @OneToOne
    @JoinColumn(name = "factorial", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @OneToOne
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;

    public CustAndDCProduct(){}

    public Long getId() {
        return id;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public DCsAndFactories getdCsAndFactories() {
        return dCsAndFactories;
    }

    public void setdCsAndFactories(DCsAndFactories dCsAndFactories) {
        this.dCsAndFactories = dCsAndFactories;
    }

}
