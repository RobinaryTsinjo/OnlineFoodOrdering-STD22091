package com.mealordering.mealordering.controller;

import com.mealordering.mealordering.entity.Customer;
import com.mealordering.mealordering.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() throws SQLException {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) throws SQLException {
        customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws SQLException {
        customer.setCustomerId(customerId);
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) throws SQLException {
        customerService.deleteCustomerById(customerId);
    }
}
