package logistics.entityes;


import javax.persistence.*;

@Entity
@Table(name = "icon")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "facility_type")
    private String facility;

    @Column(name = "is_delault")
    private boolean flag;

    @Column(name = "original_icon")
    private String originalUrl;

    @Column(name = "one_and_half_icon")
    private String oneHalfUrl;

    @Column(name = "twice_icon")
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
