package com.mealordering.mealordering.entity;

import java.util.Objects;

public class Dish {
    private Integer dishId;
    private String name;
    private String description;
    private double price;
    private String image;

    public Dish() {
    }

    public Dish(Integer dishId, String name, String description, double price, String image) {
        this.dishId = dishId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 &&
                Objects.equals(dishId, dish.dishId) &&
                Objects.equals(name, dish.name) &&
                Objects.equals(description, dish.description) &&
                Objects.equals(image, dish.image);
    }


    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, description, price, image);
    }
}
