package com.premthomas.foodieapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order1 {
    /*
    add the following properties
    --------------------------------------
    Datatype                  variable
    --------------------------------------
    String                      id
    Customer                    customer
    Restaurant                  restaurant
    List<Dish>                  dishes
    double                      price
     */

    /*
    1. All the fields should be private
    2. Create only no-arg constructor
    3. Create Getters and Setter methods
    4. Override hashCode() and equals() methods
    5. Override toString() methods
     */
    private String id;
    private Customer customer;
    private Restaurant restaurant;
    private List<Dish> dishes;
    private double price;
    private LocalDate orderDate;

    public Order1() {
    }

    public String getId() {
        return id;
    }

    public Order1 setId(String id) {
        this.id = id;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Order1 setPrice(double price) {
        this.price = price;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order1 setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Order1 setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Order1 setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Order1 setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order1 order1 = (Order1) o;
        return Double.compare(price, order1.price) == 0 && Objects.equals(id, order1.id) && Objects.equals(customer, order1.customer) && Objects.equals(restaurant, order1.restaurant) && Objects.equals(dishes, order1.dishes) && Objects.equals(orderDate, order1.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, restaurant, dishes, price, orderDate);
    }

    @Override
    public String toString() {
        return "Order1{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                ", price=" + price +
                ", orderDate=" + orderDate +
                '}';
    }
}
