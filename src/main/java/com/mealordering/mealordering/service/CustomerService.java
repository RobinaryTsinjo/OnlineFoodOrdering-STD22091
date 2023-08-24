package com.mealordering.mealordering.service;

import com.mealordering.mealordering.entity.Customer;
import com.mealordering.mealordering.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepository.findAllCustomers();
    }
    public Customer getCustomerById(int customerId) throws SQLException {
        return customerRepository.findCustomerById(customerId);
    }
    public Customer getCustomerByName(String name) throws SQLException {
        return customerRepository.findCustomerByName(name);
    }
    public int addCustomer(Customer customer) throws SQLException {
       return customerRepository.addCustomer(customer);

    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomerById(int customerId) throws SQLException {
        customerRepository.deleteCustomerById(customerId);
    }
}
