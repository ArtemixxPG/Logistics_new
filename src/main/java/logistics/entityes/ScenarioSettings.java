package logistics.entityes;


import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
@Table(name = "scenario_settings")
public class ScenarioSettings {

    @Id //Поле айди
    @GeneratedValue(strategy = GenerationType.AUTO)//Автогенерация ключа
    @Column(name = "ID") //Наименование поля в таблице
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "descriptiion")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="creation")
    private Date creation;

    @Column(name = "scenario")
    private String scenario;

    public ScenarioSettings(){}


    public Long getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
}
