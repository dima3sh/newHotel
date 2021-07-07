package org.azati.cources.entity;

public class Food extends Thing {
    private Integer dish_id;
    private Integer caloric;

    public Food(){}

    public Food(String name, Double weight, Integer costPerObject, Integer dish_id, Integer caloric) {
        super(name, weight, costPerObject);
        this.dish_id = dish_id;
        this.caloric = caloric;
    }

    public Integer getDish_id() {
        return dish_id;
    }

    public Integer getCaloric() {
        return caloric;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
    }

    public void setCaloric(Integer caloric) {
        this.caloric = caloric;
    }

    @Override
    public String toString() {
        return "Food{" +
                "dish_id=" + dish_id +
                ", caloric=" + caloric +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return dish_id.equals(food.dish_id) && caloric.equals(food.caloric) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(dish_id);
        result = 31 * result + Integer.hashCode(caloric);
        result = 31 * result + super.hashCode();
        return result;
    }
}
