package cn.yaogang.budgetservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Transaction implements Model, Serializable {
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Operation operation;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double amount;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", operation=" + operation +
                ", value=" + value +
                ", total=" + total +
                ", amount=" + amount +
                '}';
    }

    public enum Operation {
        ADD("ADD"), REMOVE("REMOVE");
        private String op;

        Operation(String op) {
            this.op = op;
        }
    }
}
