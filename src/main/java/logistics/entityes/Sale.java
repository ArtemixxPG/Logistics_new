package logistics.entityes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer")
    private String customers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "name")
    private Products products;

    @Column(name = "id_sale")
    private String id_sale;

    @Column(name = "priority")
    private String priority;

    @Column(name = "prim")
    private String prim;
}
