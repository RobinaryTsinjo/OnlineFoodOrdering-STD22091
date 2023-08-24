package com.mealordering.mealordering.repository;

import com.mealordering.mealordering.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private final Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour récupérer tous les clients
    public List<Customer> findAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setAddress(resultSet.getString("address"));
                customers.add(customer);
            }
        }
        return customers;
    }
    public Customer findCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("customer_id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setPhoneNumber(resultSet.getString("phone_number"));
                    customer.setAddress(resultSet.getString("address"));
                    return customer;
                }
            }
        }
        return null; // Si aucun client avec cet ID n'est trouvé
    }
    // Méthode pour ajouter un client
    public int addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(name, phone_number, address) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhoneNumber());
            statement.setString(3, customer.getAddress());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to create customer, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to create customer, no ID generated.");
                }
            }
        }
        return 0;
    }


    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?, password = ?, phone_number = ?, address = ? WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(3, customer.getPhoneNumber());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getCustomerId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un client par ID
    public void deleteCustomerById(int customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }



    public Customer findCustomerByName(String name) throws SQLException {
        String sql = "SELECT * FROM customer WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Customer(
                            resultSet.getInt("customer_id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("address")
                    );
                } else {
                    return null;
                }
            }
        }
    }


}
