package logistics.entityes;

import org.springframework.aop.IntroductionAwareMethodMatcher;

import javax.persistence.*;

@Entity
@Table(name = "cash_account")
public class CashAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "initial_cash")
    private double initial_cash;

    @Column(name = "interest")
    private String interest;

    @OneToOne(mappedBy = "cashAccount")
    private CashAccountsFacilities cashAccountsFacilities;

    public CashAccount(){}

    public Long getId() {
        return id;
    }

    public double getInitial_cash() {
        return initial_cash;
    }

    public String getInterest() {
        return interest;
    }

    public String getName() {
        return name;
    }

    public void setInitial_cash(double initial_cash) {
        this.initial_cash = initial_cash;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CashAccountsFacilities getCashAccountsFacilities() {
        return cashAccountsFacilities;
    }

    public void setCashAccountsFacilities(CashAccountsFacilities cashAccountsFacilities) {
        this.cashAccountsFacilities = cashAccountsFacilities;
    }
}
