package com.mealordering.mealordering.service;

import com.mealordering.mealordering.entity.OrderItem;
import com.mealordering.mealordering.repository.OrderItemRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Méthode pour ajouter un élément de commande
    public void addOrderItem(OrderItem orderItem) throws SQLException {
        orderItemRepository.addOrderItem(orderItem);
    }

    // Méthode pour récupérer les éléments de commande par ID de commande
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }

    // Méthode pour mettre à jour un élément de commande
    public void updateOrderItem(OrderItem orderItem) throws SQLException {
        orderItemRepository.updateOrderItem(orderItem);
    }

    // Méthode pour supprimer un élément de commande
    public void deleteOrderItem(int orderId, int dishId) throws SQLException {
        orderItemRepository.deleteOrderItem(orderId, dishId);
    }
}
