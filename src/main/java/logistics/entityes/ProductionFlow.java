package logistics.entityes;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "production_flow")
public class ProductionFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iteration")
    private Long iteration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "period", referencedColumnName = "name")
    private TimePeriod timePeriod;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility", referencedColumnName = "name")
    private DCsAndFactories dCsAndFactories;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bom", referencedColumnName = "name")
    private BOM bom;

    @Column(name = "consumed")
    private Long consumed;

    @Column(name = "produced")
    private Long produced;




}
