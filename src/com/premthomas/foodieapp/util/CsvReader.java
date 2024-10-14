package com.premthomas.foodieapp.util;

import com.premthomas.foodieapp.model.Customer;
import com.premthomas.foodieapp.model.Dish;
import com.premthomas.foodieapp.model.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    // CSV files read and create list of Subjects
private List<Dish> dishesList;
private List<Customer> customerList;

    public List<Dish> readDishesFromCsv(){
        String DISHES_CSV_PATH = "C:\\Users\\erapo\\StephenThomas\\FoodApplcation12Oct\\data\\dishes (1).csv";
        String line;
        List<Dish> dishesList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(DISHES_CSV_PATH))){
           String csvSplitBy = ",";
           br.readLine();
           while((line = br.readLine()) != null){
               String[] data = line.split(csvSplitBy);
               Dish dish = new Dish();
               dish.setId(data[0])
                       .setName(data[1])
                       .setDescription(data[2])
                       .setPrice(Double.parseDouble(data[3]));
               dishesList.add(dish);

           }

           this.dishesList = dishesList;
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Issues in reading csv file from the path :" + DISHES_CSV_PATH);
            System.exit(0);
        }
        return dishesList;
    }




    public List<Customer> readCustomersFromCsv(){
        String CUSTOMERS_CSV_PATH = "C:\\Users\\erapo\\StephenThomas\\FoodApplcation12Oct\\data\\customers (1).csv";
        String line ;
        List<Customer> customerList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_CSV_PATH))){
             String csvSplitBy = ",";;
             br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(csvSplitBy);
               Customer customer = new Customer();
                 customer.setId(data[0])
                         .setName(data[1])
                         .setEmail(data[2])
                         .setPassword(data[3]);
               customerList.add(customer);

            }

        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Issue in reading csv file from the path : " + CUSTOMERS_CSV_PATH);
            System.exit(0);
        }
        return customerList;
    }


    public List<Restaurant> readRestaurantFromCSv(){
        String RESTAURANT_CSV_PATH = "C:\\Users\\erapo\\StephenThomas\\FoodApplcation12Oct\\data\\restaurants (1).csv ";
        String line;
        List<Restaurant> restaurantList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(RESTAURANT_CSV_PATH))){
            String csvSplitBy = ",";
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] data = line.split(csvSplitBy);  // click on method after ctr + alt +v it create method
                Restaurant restaurant = new Restaurant();
                restaurant.setId(data[0])
                        .setName(data[1])
                        .setAddress(data[2])
                        .setMenu(Arrays.asList(data[3].split(":")));
                restaurantList.add(restaurant);
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Issue in reading csv file from the Path : " + RESTAURANT_CSV_PATH);
            System.exit(0);
        }
        return restaurantList;
    }

}
