package br.com.ejb.projetomobile.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Chart extends SugarRecord {
    private String status;
    private String name;
    private String unit;
    private String period;
    private String description;
    @Ignore
    private List<Value> values;

    public Chart() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
