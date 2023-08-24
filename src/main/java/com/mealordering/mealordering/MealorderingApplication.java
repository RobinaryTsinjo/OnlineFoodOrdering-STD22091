package com.mealordering.mealordering;
import com.mealordering.mealordering.db.DatabaseConnection;
import com.mealordering.mealordering.repository.CustomerRepository;
import com.mealordering.mealordering.repository.DishRepository;
import com.mealordering.mealordering.repository.OrderItemRepository;
import com.mealordering.mealordering.repository.OrderRepository;
import com.mealordering.mealordering.service.CustomerService;
import com.mealordering.mealordering.service.DishService;
import com.mealordering.mealordering.service.OrderItemService;
import com.mealordering.mealordering.service.OrderService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MealorderingApplication {

	public static void main(String[] args) {
		CustomerService customerService = new CustomerService(new CustomerRepository(DatabaseConnection.getConnection()));
		DishService dishService = new DishService(new DishRepository(DatabaseConnection.getConnection()));
		OrderService orderService = new OrderService(new OrderRepository(DatabaseConnection.getConnection()));
		OrderItemService orderItemService = new OrderItemService(new OrderItemRepository(DatabaseConnection.getConnection()));

		CommandLineMenu menu = new CommandLineMenu(customerService, dishService, orderService);
		menu.displayMenu();
	}
}


