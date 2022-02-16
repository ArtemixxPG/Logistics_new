package logistics.entityes;

import com.fasterxml.jackson.annotation.JsonView;
import logistics.view.View;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Location")
public class Locations implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;

    @Column(name = "Code")
    @JsonView(View.Public.class)
    private String code;

    @Column(name = "name")
    @JsonView(View.Public.class)
    private String name;

    @Column(name = "city")
    @JsonView(View.Public.class)
    private String city;

    @Column(name = "region")
    @JsonView(View.Public.class)
    private String region;

    @Column(name = "country")
    @JsonView(View.Public.class)
    private String country;

    @Column(name = "address")
    @JsonView(View.Public.class)
    private String address;

    @Column(name = "latitude")
    @JsonView(View.Public.class)
    private Double latitude;

    @Column(name = "longitude")
    @JsonView(View.Public.class)
    private Double longitude;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(View.Internal.class)
    private Suppliers suppliers;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(View.Internal.class)
    private DCsAndFactories dCsAndFactories;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(View.Internal.class)
    private Customers customers;

    public Locations(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Customers getCustomers() {
        return customers;
    }

    public DCsAndFactories getdCsAndFactories() {
        return dCsAndFactories;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public void setdCsAndFactories(DCsAndFactories dCsAndFactories) {
        this.dCsAndFactories = dCsAndFactories;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

}
