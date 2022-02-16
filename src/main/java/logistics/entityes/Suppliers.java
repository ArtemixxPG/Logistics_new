package logistics.entityes;

import logistics.entityes.Locations;
import logistics.entityes.Participant;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Suppliers extends Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;


    @Column(name = "inclusion_type")
    private String inclusion_type;

    @Column(name = "icon")
    private int icon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "name")
    private Locations locations;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "site_state")
//    private List<SiteState> siteStates;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Locations getLocation() {
        return locations;
    }

    public void setLocation(Locations locations) {
        this.locations = locations;
    }

    public String getInclusion_type() {
        return inclusion_type;
    }

    public void setInclusion_type(String inclusion_type) {
        this.inclusion_type = inclusion_type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
