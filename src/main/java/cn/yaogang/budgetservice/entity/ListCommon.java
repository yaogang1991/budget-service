package cn.yaogang.budgetservice.entity;

import cn.yaogang.budgetservice.model.Model;

import java.io.Serializable;
import java.util.List;

public class ListCommon<T extends Model> implements Serializable {
    private int size;
    private List<T> models;

    public ListCommon(List<T> models) {
        this.models = models;
        this.size = models.size();
    }

    public int getSize() {
        return size;
    }

    public List<T> getModels() {
        return models;
    }

    @Override
    public String toString() {
        return "ListCommon{" +
                "size=" + size +
                ", models=" + models +
                '}';
    }
}
