package cn.yaogang.budgetservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expenditure_type")
public class ExpenditureType implements Serializable, Model {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public ExpenditureType() {
    }

    public ExpenditureType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExpenditureType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
