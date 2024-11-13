package com.premthomas.foodieapp.ui;

import com.premthomas.foodieapp.controller.DishController;
import com.premthomas.foodieapp.exceptions.DishExistsException;
import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Dish;

import java.util.List;
import java.util.Scanner;

public class DishesMenu extends Menu{
    private final DishController dishController;

    public DishesMenu(){
        this.dishController = Factory.getDishController();
    }

    @Override
    public void displayMenu(){
        try{
            Scanner scanner = new Scanner(System.in);
            while(true){
                displayMenuHeader("WELCOME TO DISHES SECTION");
                System.out.println();
                System.out.println("PLease select the Option !");
                System.out.println("--------------------------");
                System.out.println("1. Add New Dish");
                System.out.println("2. View All Dish Items");
                System.out.println("3. Search Dish");
                System.out.println("4. Update Dish");
                System.out.println("5. Delete Dish");
                System.out.println("6. Exit");
                System.out.println("Please enter Your choice(1-6)");


                int input = scanner.nextInt();
                switch (input){
                    case 1 -> newDishForm();
                    case 2 -> displayDishes();
                    case 3 -> dishSearchForm();
                    case 4 -> dishUpdateForm();
                    case 5 -> dishDeleteForm();
                    case 6 -> {
                        System.out.println("Thank You, See You Again!.");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input.Please enter the valid input from(1-7)");
                }

            }
        } catch(Exception e){
            System.out.println("Some internal error occurred. Please try again !..");
            displayMenu();
        }
    }

    public void dishUpdateForm(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Update entering the following details\n");
            System.out.println("Enter Dish Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);
            Dish updateDish = dishController.update(dish);
            System.out.println("Dish Updated Successfully");
            displayDish(updateDish);
        } catch(DishNotFoundException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Some internal error occurred. Please try again !");
            dishUpdateForm();
        }
    }

    public void dishDeleteForm(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to delete the Dish\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            dishController.deleteDish(id);
            System.out.println("Dish Deleted Successfully");
        } catch (DishNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void dishSearchForm(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Following to Search for Customer\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Dish dish =dishController.getDishById(id);
            displayDish(dish);
        } catch (DishNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void newDishForm(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the Following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);
            Dish savedDish = this. dishController.save(dish);
            displayDish(savedDish);
        } catch (DishExistsException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Some internal error occorred. Please try again!");
            newDishForm();
        }
    }

    public void displayDishes(){
        List<Dish> dishesList = this.dishController.getDishList();
        String dashesLine = new String(new char[150]).replace('\0','-');
        displayMenuHeader("Dish Items");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name","Description","Price");
        System.out.println(dashesLine);
        dishesList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(),dish.getDescription(),String.format("$%.2f", dish.getPrice()));
        });
    }

    public void displayDish(Dish dish){
        displayMenuHeader("Dish Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(),String.format("$%.2f", dish.getPrice()));
    }
}
