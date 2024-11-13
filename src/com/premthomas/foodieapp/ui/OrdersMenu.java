package com.premthomas.foodieapp.ui;

import com.premthomas.foodieapp.controller.OrderController;
import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.exceptions.OrderExistsException;
import com.premthomas.foodieapp.exceptions.OrderNotFoundException;
import com.premthomas.foodieapp.exceptions.RestaurantNotFoundException;
import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Customer;
import com.premthomas.foodieapp.model.Dish;
import com.premthomas.foodieapp.model.Order1;
import com.premthomas.foodieapp.model.Restaurant;
import com.premthomas.foodieapp.service.CustomerService;
import com.premthomas.foodieapp.service.DishService;
import com.premthomas.foodieapp.service.RestaurantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrdersMenu extends Menu{
    private  final OrderController orderController;

    public OrdersMenu() {
        this.orderController = Factory.getOrderController();
    }

    @Override
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO ORDERS SECTIONS");
                System.out.println();
                System.out.println("Please select the Option !!");
                System.out.println("----------------------------");
                System.out.println("1. Place New Order");
                System.out.println("2. Search Order");
                System.out.println("3. View All Orders");
                System.out.println("4. Exit");
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> newOrderForm();
                    case 2 -> searchOrderForm();
                    case 3 -> orderList();
                    case 4 -> {
                        System.out.println("Thank You, See You Again!..");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid input. Please enter the valid input from (1-4)");
                }

            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !..");
            displayMenu();
        }
    }

    private  void orderList(){
        List<Order1> order1List = this.orderController.getOrdersList();
        displayMenuHeader("All Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
        printDashLine();

        order1List.forEach(order ->{
            String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
            System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", order.getId(),order.getRestaurant().getName(),dishNames, order.getOrderDate(),order.getPrice());
        } );
        System.out.println("\n\n");
    }

    private void searchOrderForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to search for Order\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Order1 order = orderController.getOrderById(id);
            displayOrderDetails(order);

        }catch(OrderNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();

        }
    }
    private void displayOrderDetails(Order1 order){
        String dishNames = order.getDishes().stream().map(Dish::getName).collect(Collectors.joining(","));
        displayMenuHeader("Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n ", "Id", "Customer Name", "Restaurant Name", "Items","Order Date","Price");
        printDashLine();
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n", order.getId(),order.getCustomer().getName(),order.getRestaurant().getName(),dishNames,order.getOrderDate(),String.format("$%.2f",order.getPrice()));
    }

    private void newOrderForm() throws DishNotFoundException{
        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.getCustomerService();
            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService =Factory.getDishService();
            loggedInCustomer = customerService.getCurrectLoggedCustomer();
            if(loggedInCustomer != null)
                System.out.println("Hello, "+ loggedInCustomer.getName());
            while(loggedInCustomer == null){
                System.out.println("Please login to Place an Order");
                new CustomerMenu().customerLoginForm();
                loggedInCustomer = customerService.getCurrectLoggedCustomer();
            }
            System.out.println("Entr Order Id :");
            String id = scanner.nextLine();
            while(restaurant == null){
                new RestaurantMenu().displayRestaurants();
                printDashLine();
                System.out.println("Choose the Restaurant Id (Ex: R08)");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
            }
            char addMoreItems = 'Y';
            while(addMoreItems == 'Y'){
                new RestaurantMenu().displayMenuItems(restaurant.getId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex : D001)");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("One Dish is added Successfully : "+ selectedDish.getName());
                System.out.println("Doyou want to add more dishes (Y/N)");
                addMoreItems = scanner.nextLine().charAt(0);
            }
            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();

            Order1 order = new Order1();
            order.setId(id)
                    .setCustomer(loggedInCustomer)
                    .setRestaurant(restaurant)
                    .setDishes(dishList)
                    .setPrice(orderPrice)
                    .setOrderDate(orderDate);

            Order1 placeOrder = orderController.saveOrder(order);
            if(placeOrder != null)
                System.out.println("Order Placed Successfully with the following details");
            displayOrderDetails(placeOrder);
        } catch(RestaurantNotFoundException| OrderExistsException e){
            System.out.println(e.getMessage());
        }
    }
    private double calculateOrderTotalPrice(List<Dish> dishList){
        return dishList.stream().mapToDouble(Dish::getPrice).sum();
    }
}
