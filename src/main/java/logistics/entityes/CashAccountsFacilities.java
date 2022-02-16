package logistics.entityes;

import javax.persistence.*;

@Entity
@Table(name = "cash_account_facilities")
public class CashAccountsFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cash_account", referencedColumnName = "id")
    private CashAccount cashAccount;

    @Column(name = "contents")
    private String content;

    public CashAccountsFacilities(){
    }

    public Long getId() {
        return id;
    }

    public CashAccount getCashAccount() {
        return cashAccount;
    }

    public void setCashAccount(CashAccount cashAccount) {
        this.cashAccount = cashAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
