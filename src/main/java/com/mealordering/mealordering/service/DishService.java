package com.mealordering.mealordering.service;

import com.mealordering.mealordering.entity.Dish;
import com.mealordering.mealordering.repository.DishRepository;

import java.sql.SQLException;
import java.util.List;

public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() throws SQLException {
        return dishRepository.findAllDishes();
    }

    public void addDish(Dish dish) throws SQLException {
        dishRepository.addDish(dish);
    }

    public void updateDish(Dish dish) throws SQLException {
        dishRepository.updateDish(dish);
    }
    public Dish getDishById(int dishId) throws SQLException {
        return dishRepository.getDishById(dishId);
    }

    public void deleteDishById(int dishId) throws SQLException {
        dishRepository.deleteDishById(dishId);
    }
}
