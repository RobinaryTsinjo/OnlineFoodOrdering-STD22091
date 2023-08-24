package com.mealordering.mealordering.repository;

import com.mealordering.mealordering.entity.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishRepository {








    private final Connection connection;

    public DishRepository(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour récupérer tous les plats
    public List<Dish> findAllDishes() throws SQLException {
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM dish";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("name"));
                dish.setDescription(resultSet.getString("description"));
                dish.setPrice(resultSet.getDouble("price"));
                dish.setImage(resultSet.getString("image"));
                dishes.add(dish);
            }
        }
        return dishes;
    }

    // Méthode pour ajouter un plat
    public void addDish(Dish dish) throws SQLException {
        String sql = "INSERT INTO dish(name, description, price, image) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getPrice());
            statement.setString(4, dish.getImage());
            statement.executeUpdate();
        }
    }

    public void updateDish(Dish dish) throws SQLException {
        String sql = "UPDATE dish SET name = ?, description = ?, price = ?, image = ? WHERE dish_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getPrice());
            statement.setString(4, dish.getImage());
            statement.setInt(5, dish.getDishId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un plat par ID
    public void deleteDishById(int dishId) throws SQLException {
        String sql = "DELETE FROM dish WHERE dish_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dishId);
            statement.executeUpdate();
        }
    }

    public Dish getDishById(int dishId) throws SQLException{

        String sql = "SELECT * FROM dish WHERE dish_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dishId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Dish dish = new Dish();
                    dish.setDishId(resultSet.getInt("dish_id"));
                    dish.setName(resultSet.getString("name"));
                    dish.setDescription(resultSet.getString("description"));
                    dish.setPrice(resultSet.getDouble("price"));
                    dish.setImage(resultSet.getString("image"));
                    return dish;
                } else {
                    return null;
                }
            }
        }


    }
}
