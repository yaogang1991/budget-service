package cn.yaogang.budgetservice.entity;

public class Own {
    private String code;
    private double principal;
    private double total;
    private double amount;
    private double rate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Own{" +
                "code='" + code + '\'' +
                ", principal=" + principal +
                ", total=" + total +
                ", amount=" + amount +
                ", rate=" + rate +
                '}';
    }
}
