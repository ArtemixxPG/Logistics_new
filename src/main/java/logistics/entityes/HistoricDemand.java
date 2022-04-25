package logistics.entityes;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "historic_demand")
public class HistoricDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Transient
    @CsvBindByName(column = "Period")
    private String periodName;


    @OneToOne
    @JoinColumn(name = "id_demand", referencedColumnName = "id_demand")
    private Demand demand;

    @OneToOne
    @JoinColumn(name="date", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @Column(name = "count")
    @CsvBindByName(column = "Count")
    private Long count;

    public HistoricDemand(){}


    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public Long getId() {
        return id;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
}
