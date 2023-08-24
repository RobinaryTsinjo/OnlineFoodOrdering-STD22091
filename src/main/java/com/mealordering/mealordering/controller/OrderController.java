package com.mealordering.mealordering.controller;

import com.mealordering.mealordering.entity.Order;
import com.mealordering.mealordering.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<Order> getAllOrders() throws SQLException {
        return orderService.getAllOrders();
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) throws SQLException {
        orderService.addOrder(order);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable int orderId, @RequestBody Order order) throws SQLException {
        order.setOrderId(orderId);
        orderService.updateOrder(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) throws SQLException {
        orderService.deleteOrderById(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable int customerId) throws SQLException {
        return orderService.getOrdersByCustomerId(customerId);
    }

}
