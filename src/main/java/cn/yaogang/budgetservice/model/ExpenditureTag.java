package cn.yaogang.budgetservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expenditure_tag")
public class ExpenditureTag implements Serializable, Model {
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public ExpenditureTag() {
    }

    public ExpenditureTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ExpenditureTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
