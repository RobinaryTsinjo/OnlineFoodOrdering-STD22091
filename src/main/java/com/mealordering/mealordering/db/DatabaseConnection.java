package com.mealordering.mealordering.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private  DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/meal_ordering",
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            );
        } catch (SQLException e) {
            System.out.println("Error while connecting to database : " + e.getMessage());
        }
    }
    public  static Connection getConnection(){
        if (connection == null){
            new DatabaseConnection();
        }
        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection.getConnection();
    }
}
