package com.mealordering.mealordering.entity;

import java.util.List;
import java.util.Objects;

public class OrderItem {
    private int orderId;
    private int dishId;
    private int quantity;
    private Dish dish;


    public OrderItem() {
    }

    public OrderItem(int orderId, int dishId, int quantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }
    public Dish getDish() {
        return dish;
    }
    public void setOrder(Order order) {
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", dishId=" + dishId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dishId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderItem orderItem = (OrderItem) obj;
        return orderId == orderItem.orderId && dishId == orderItem.dishId;
    }



}
