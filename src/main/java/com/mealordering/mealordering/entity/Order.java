package com.mealordering.mealordering.entity;


//import com.mealordering.mealordering.service.
import com.mealordering.mealordering.service.CustomerService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Integer orderId;
    private Timestamp orderDatetime;
    private String orderStatus;
    private String deliveryAddress;
    private Integer customerId;
    private Integer dishId;


    public Order(Integer orderId, Timestamp orderDatetime, String orderStatus,
                 String deliveryAddress, Integer customerId, Integer dishId) {
        this.orderId = orderId;
        this.orderDatetime = orderDatetime;this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.customerId = customerId;
        this.dishId = dishId;

    }

    public Order() {

    }

    public Customer getCustomer(CustomerService customerService) throws SQLException {
        return customerService.getCustomerById(customerId);
    }



    public String getStatus() {
        return orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Timestamp orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }



    public void setCustomer(Customer customer) {
    }

    public void setStatus(String status) {
        this.orderStatus = status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDatetime=" + orderDatetime +
                ", orderStatus='" + orderStatus + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", customerId=" + customerId +
                ", dishId=" + dishId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(orderDatetime, order.orderDatetime) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(dishId, order.dishId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDatetime, orderStatus, deliveryAddress, customerId, dishId);
    }



}
