package cn.yaogang.budgetservice.entity;

import java.io.Serializable;

public class State implements Serializable {
    private static final String OK = "OK!";

    private Boolean success;
    private String msg;

    public State() {
        this.success = true;
        this.msg = OK;
    }

    public State(Exception e) {
        this.success = false;
        this.msg = e.getMessage();
    }

    public State(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "State{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
