package org.azati.cources.entity;


import java.util.List;

public class Dish extends Thing{
    private Integer caloric;
    private Integer dish_id;
    private List<Food> foods;

    public Dish(){}

    public Dish(String name, Double weight, Integer costPerObject, Integer caloric, Integer dish_id, List<Food> foods) {
        super(name, weight, costPerObject);
        this.caloric = caloric;
        this.dish_id = dish_id;
        this.foods = foods;
    }

    public Integer getCaloric() {
        return caloric;
    }

    public Integer getDish_id() {
        return dish_id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setCaloric(Integer caloric) {
        this.caloric = caloric;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "caloric=" + caloric +
                ", foods=" + foods +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return caloric.equals(dish.caloric) && dish_id.equals(dish.dish_id) && foods.equals(dish.foods) && super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(caloric);
        result = 31 * result + Integer.hashCode(dish_id);
        result = 31 * result + foods.hashCode();
        result = 31 * result + super.hashCode();
        return result;
    }
}
