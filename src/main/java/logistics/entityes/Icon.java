package logistics.entityes;


import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;

@Entity
@Table(name = "icon")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "facility_type")
    @CsvBindByName(column = "Facility")
    private String facility;

    @Column(name = "is_delault")
    @CsvBindByName(column = "Is Default")
    private boolean flag;

    @Column(name = "original_icon")
    @CsvBindByName(column = "Original Icon")
    private String originalUrl;

    @Column(name = "one_and_half_icon")
    @CsvBindByName(column = "One and half icon")
    private String oneHalfUrl;

    @Column(name = "twice_icon")
    @CsvBindByName(column = "Twice icon")
    private String twiceIcon;

    public Icon(){}


    public Long getId() {
        return id;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOneHalfUrl() {
        return oneHalfUrl;
    }

    public void setOneHalfUrl(String oneHalfUrl) {
        this.oneHalfUrl = oneHalfUrl;
    }

    public String getTwiceIcon() {
        return twiceIcon;
    }

    public void setTwiceIcon(String twiceIcon) {
        this.twiceIcon = twiceIcon;
    }
}
