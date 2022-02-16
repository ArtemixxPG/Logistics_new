package logistics.entityes;

import javax.persistence.*;

@Entity
@Table(name = "project_unit")
public class ProjectUnits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "enabled_unit")
    private String unit;

    @Column(name = "unit_type")
    private String unit_type;


    public ProjectUnits(){}


    public Long getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }
}
