package com.mealordering.mealordering.repository;

import com.mealordering.mealordering.entity.Order;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour récupérer toutes les commandes
    public List<Order> findAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM \"order\"";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setDeliveryAddress(resultSet.getString("delivery_address"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setDishId(resultSet.getInt("dish_id"));
                orders.add(order);
            }
        }
        return orders;
    }



    // Méthode pour mettre à jour une commande
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE \"order\" SET order_datetime = ?, order_status = ?, delivery_address = ?, customer_id = ?, dish_id = ? WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, order.getOrderDatetime());
            statement.setString(2, order.getOrderStatus());
            statement.setString(3, order.getDeliveryAddress());
            statement.setInt(4, order.getCustomerId());
            statement.setInt(5, order.getDishId());
            statement.setInt(6, order.getOrderId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer une commande par ID
    public void deleteOrderById(int orderId) throws SQLException {
        String sql = "DELETE FROM \"order\" WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }

    }
    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO \"order\" (order_datetime, order_status, delivery_address, customer_id, dish_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, order.getOrderDatetime());
            statement.setString(2, order.getOrderStatus());
            statement.setString(3, order.getDeliveryAddress());
            statement.setInt(4, order.getCustomerId());

            Integer dishId = order.getDishId();
            if (dishId == null) {
                dishId = 1; // Assign a default value or handle the case accordingly
            }
            statement.setInt(5, dishId);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to get generated order ID.");
                }
            }
        }
    }

    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM \"order\" WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    // Set the order attributes here
                    orders.add(order);
                }
            }
        }
        return orders;
    }
    public Order findActiveOrderByCustomerId(int customerId) throws SQLException {
        String sql = "SELECT * FROM \"order\" WHERE customer_id = ? AND order_status = 'En cours'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                    order.setOrderStatus(resultSet.getString("order_status"));
                    order.setDeliveryAddress(resultSet.getString("delivery_address"));
                    order.setCustomerId(resultSet.getInt("customer_id"));
                    order.setDishId(resultSet.getInt("dish_id"));
                    return order;
                }
            }
        }
        return null;
    }



}

