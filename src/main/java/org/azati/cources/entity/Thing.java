package org.azati.cources.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@MappedSuperclass

public class Thing {

    @Column(name = "name_equipment")
    protected String name;

    @Column(name = "weight")
    protected Double weight;

    @Min(value = 1, message = "weight should not be less than 1")
    @Max(value = 65, message = "weight should not be greater than 65")
    @Column(name = "cost_per_object")
    protected Integer costPerObject;

    public Thing() {
    }

    public Thing(String name, Double weight, Integer costPerObject) {
        this.name = name;
        this.weight = weight;
        this.costPerObject = costPerObject;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getCostPerObject() {
        return costPerObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setCostPerObject(Integer costPerObject) {
        this.costPerObject = costPerObject;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", costPerObject=" + costPerObject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing)) return false;
        Thing thing = (Thing) o;
        return name.equals(thing.name) && weight.equals(thing.weight) && costPerObject.equals(thing.costPerObject);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + costPerObject.hashCode();
        return result;
    }
}
