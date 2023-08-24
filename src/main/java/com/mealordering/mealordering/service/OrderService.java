package com.mealordering.mealordering.service;

import com.mealordering.mealordering.entity.Order;
import com.mealordering.mealordering.entity.OrderItem;
import com.mealordering.mealordering.repository.OrderItemRepository;
import com.mealordering.mealordering.repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.findAllOrders();
    }
    public void placeOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }

    public void addOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }

    public void updateOrder(Order order) throws SQLException {
        orderRepository.updateOrder(order);
    }

    public void deleteOrderById(int orderId) throws SQLException {
        orderRepository.deleteOrderById(orderId);
    }

    public Order getActiveOrderByCustomerId(int customerId) throws SQLException {
        return orderRepository.findActiveOrderByCustomerId(customerId);
    }

    public Order findActiveOrderByCustomerId(int customerId) throws SQLException {
        return orderRepository.findActiveOrderByCustomerId(customerId);
    }
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        return orderRepository.getOrdersByCustomerId(customerId);
    }




}

