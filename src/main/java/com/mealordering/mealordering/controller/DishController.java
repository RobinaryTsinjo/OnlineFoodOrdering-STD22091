package com.mealordering.mealordering.controller;

import com.mealordering.mealordering.entity.Dish;
import com.mealordering.mealordering.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() throws SQLException {
        return dishService.getAllDishes();
    }

    @PostMapping
    public void addDish(@RequestBody Dish dish) throws SQLException {
        dishService.addDish(dish);
    }

    @PutMapping("/{dishId}")
    public void updateDish(@PathVariable int dishId, @RequestBody Dish dish) throws SQLException {
        dish.setDishId(dishId);
        dishService.updateDish(dish);
    }

    @DeleteMapping("/{dishId}")
    public void deleteDish(@PathVariable int dishId) throws SQLException {
        dishService.deleteDishById(dishId);
    }
}
