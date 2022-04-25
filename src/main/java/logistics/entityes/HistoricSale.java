package logistics.entityes;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "historic_sale")
public class HistoricSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    private Sale sale;

    @Transient
    @CsvBindByName(column = "Дата документа")
    private String periodName;

    @Temporal(TemporalType.DATE)
    @Column(name = "period")
    private Date timePeriod;

    @Column(name = "count_plan")
    private Long count_plan;

    @Column(name = "count_fact")
    private Long count_fact;
}
