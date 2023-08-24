package com.mealordering.mealordering;

import com.mealordering.mealordering.db.DatabaseConnection;
import com.mealordering.mealordering.entity.Customer;
import com.mealordering.mealordering.entity.Dish;
import com.mealordering.mealordering.entity.Order;
import com.mealordering.mealordering.entity.OrderItem;
import com.mealordering.mealordering.repository.OrderItemRepository;
import com.mealordering.mealordering.service.CustomerService;
import com.mealordering.mealordering.service.DishService;
import com.mealordering.mealordering.service.OrderService;
import com.mealordering.mealordering.service.OrderItemService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLineMenu {
    private final Scanner scanner;
    private final CustomerService customerService;
    private final DishService dishService;
    private final OrderService orderService;

    public CommandLineMenu(CustomerService customerService, DishService dishService, OrderService orderService) {
        this.scanner = new Scanner(System.in);
        this.customerService = customerService;
        this.dishService = dishService;
        this.orderService = orderService;
    }
    OrderItemService orderItemService = new OrderItemService(new OrderItemRepository(DatabaseConnection.getConnection()));

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Menu ===");
            System.out.println("1. Afficher la liste des plats");
            System.out.println("2. Passer une commande");
            System.out.println("3. Afficher ma commande");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option (1/2/3/4): ");

            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case 1 -> displayDishes();
                    case 2 -> placeOrder();
                    case 3 -> showMyOrder();
                    case 4 -> exit = true;
                    default -> System.out.println("Option invalide.Veuillez choisir une option valide.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Merci d'avoir utilisé notre service !");
    }

    private void displayDishes() throws SQLException {
        System.out.println("=== Liste des plats ===");
        dishService.getAllDishes().forEach(dish -> System.out.println(dish.getName() + " - " + dish.getPrice() + "€"));
    }
    private Dish getSelectedDish() throws SQLException {
        List<Dish> dishes = dishService.getAllDishes();

        System.out.println("Choisissez le numéro du plat que vous souhaitez commander :");
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i + 1) + ". " + dishes.get(i).getName() + " - " + dishes.get(i).getPrice() + "€");
        }

        int selectedDishIndex = scanner.nextInt();
        scanner.nextLine();

        if (selectedDishIndex >= 1 && selectedDishIndex <= dishes.size()) {
            return dishes.get(selectedDishIndex - 1);
        } else {
            System.out.println("Numéro de plat invalide.");
            return null;
        }
    }


    private void placeOrder() throws SQLException {
        System.out.print("Entrez votre nom : ");
        String customerName = scanner.nextLine();

        System.out.print("Entrez votre numéro de téléphone : ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Entrez votre adresse : ");
        String deliveryAddress = scanner.nextLine();

        Customer existingCustomer = customerService.getCustomerByName(customerName);
        if (existingCustomer == null) {

            Customer newCustomer = new Customer();
            newCustomer.setName(customerName);
            newCustomer.setPhoneNumber(phoneNumber);
            newCustomer.setAddress(deliveryAddress);

            int customerId = customerService.addCustomer(newCustomer);

            Order order = new Order();
            order.setCustomerId(customerId);
            order.setOrderDatetime(new Timestamp(System.currentTimeMillis()));
            order.setStatus("En cours");
            orderService.addOrder(order);

        } else {

            Order order = new Order();
            order.setCustomerId(existingCustomer.getCustomerId());
            order.setOrderDatetime(new Timestamp(System.currentTimeMillis()));
            order.setStatus("En cours");
            order.setDeliveryAddress(deliveryAddress);
            orderService.addOrder(order);


            System.out.println("=== Liste des plats ===");
            List<Dish> dishes = dishService.getAllDishes();
            dishes.forEach(dish -> System.out.println(dish.getDishId() + ". " + dish.getName() + " - " + dish.getPrice() + "€"));

            System.out.print("Entrez les numéros des plats que vous souhaitez commander (séparés par des virgules) : ");
            String dishIdsInput = scanner.nextLine();
            String[] dishIdsArray = dishIdsInput.split(",");
            List<Integer> selectedDishIds = new ArrayList<>();
            for (String dishIdString : dishIdsArray) {
                try {
                    int dishId = Integer.parseInt(dishIdString.trim());
                    selectedDishIds.add(dishId);
                } catch (NumberFormatException e) {
                    System.out.println("Numéro de plat invalide : " + dishIdString);
                }
            }

            if (selectedDishIds.isEmpty()) {
                System.out.println("Aucun plat sélectionné.");
                return;
            }

            for (int dishId : selectedDishIds) {
                Dish dish = dishService.getDishById(dishId);
                if (dish != null) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(order.getOrderId());
                    orderItem.setDishId(dishId);
                    orderItem.setQuantity(1);
                    orderItemService.addOrderItem(orderItem);
                } else {
                    System.out.println("Plat introuvable avec l'ID : " + dishId);
                }
            }

            System.out.println("Commande passée avec succès !");
        }
    }




    private void showMyOrder() throws SQLException {
        System.out.print("Entrez votre nom : ");
        String customerName = scanner.nextLine();

        Customer customer = customerService.getCustomerByName(customerName);
        if (customer == null) {
            System.out.println("Client introuvable. Veuillez ajouter le client d'abord.");
            return;
        }

        Order activeOrder = orderService.findActiveOrderByCustomerId(customer.getCustomerId());
        if (activeOrder == null) {
            System.out.println("Aucune commande en cours pour ce client.");
            return;
        }

        System.out.println("=== Votre commande en cours ===");
        System.out.println("Date de commande : " + activeOrder.getOrderDatetime());
        System.out.println("Statut : " + activeOrder.getOrderStatus());
        System.out.println("Adresse de livraison : " + activeOrder.getDeliveryAddress());
        System.out.println("Plats commandés : ");

        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(activeOrder.getOrderId());
        for (OrderItem orderItem : orderItems) {
            Dish dish = dishService.getDishById(orderItem.getDishId());
            if (dish != null) {
                System.out.println("- " + dish.getName() + " (x" + orderItem.getQuantity() + ")");
            }
        }
    }

}
