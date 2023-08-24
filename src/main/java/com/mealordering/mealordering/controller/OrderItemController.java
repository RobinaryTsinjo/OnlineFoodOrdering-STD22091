package com.mealordering.mealordering.controller;

import com.mealordering.mealordering.entity.OrderItem;
import com.mealordering.mealordering.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public void addOrderItem(@RequestBody OrderItem orderItem) throws SQLException {
        orderItemService.addOrderItem(orderItem);
    }

    @GetMapping("/{orderId}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId) throws SQLException {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @PutMapping("/{orderId}/{dishId}")
    public void updateOrderItem(@PathVariable int orderId, @PathVariable int dishId, @RequestBody OrderItem orderItem) throws SQLException {
        orderItem.setOrderId(orderId);
        orderItem.setDishId(dishId);
        orderItemService.updateOrderItem(orderItem);
    }

    @DeleteMapping("/{orderId}/{dishId}")
    public void deleteOrderItem(@PathVariable int orderId, @PathVariable int dishId) throws SQLException {
        orderItemService.deleteOrderItem(orderId, dishId);
    }
}
