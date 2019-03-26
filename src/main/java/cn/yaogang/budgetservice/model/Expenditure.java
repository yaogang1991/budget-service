package cn.yaogang.budgetservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expenditure")
public class Expenditure implements Serializable, Model {
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private Double value;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private ExpenditureType expenditureType;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Set<ExpenditureTag> tags = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime time;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setExpenditureType(ExpenditureType expenditureType) {
        this.expenditureType = expenditureType;
    }

    public void setTags(Set<ExpenditureTag> tags) {
        this.tags = tags;
    }

    public Double getValue() {
        return value;
    }

    public ExpenditureType getExpenditureType() {
        return expenditureType;
    }

    public Set<ExpenditureTag> getTags() {
        return tags;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "id=" + id +
                ", value=" + value +
                ", expenditureType=" + expenditureType +
                ", tags=" + tags +
                ", time=" + time +
                ", description='" + description + '\'' +
                '}';
    }
}
