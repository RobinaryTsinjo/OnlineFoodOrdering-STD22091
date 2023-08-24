package com.mealordering.mealordering.repository;

import com.mealordering.mealordering.entity.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private final Connection connection;

    public OrderItemRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO order_item(order_id, dish_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getDishId());
            statement.setInt(3, orderItem.getQuantity());
            statement.executeUpdate();

        }
    }
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(resultSet.getInt("order_id"));
                    orderItem.setDishId(resultSet.getInt("dish_id"));
                    orderItem.setQuantity(resultSet.getInt("quantity"));
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    // Méthode pour mettre à jour un élément de commande
    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE order_item SET quantity = ? WHERE order_id = ? AND dish_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getQuantity());
            statement.setInt(2, orderItem.getOrderId());
            statement.setInt(3, orderItem.getDishId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un élément de commande
    public void deleteOrderItem(int orderId, int dishId) throws SQLException {
        String sql = "DELETE FROM order_item WHERE order_id = ? AND dish_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.setInt(2, dishId);
            statement.executeUpdate();
        }
    }
}