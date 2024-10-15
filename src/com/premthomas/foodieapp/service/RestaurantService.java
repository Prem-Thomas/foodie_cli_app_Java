package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.exceptions.RestaurantExistsException;
import com.premthomas.foodieapp.exceptions.RestaurantNotFoundException;
import com.premthomas.foodieapp.model.Dish;
import com.premthomas.foodieapp.model.Restaurant;
import com.sun.source.tree.ReturnTree;

import java.util.List;

public interface RestaurantService {
    public List<Restaurant> getRestaurantList();
    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException;
    public Restaurant getRestaurantById(String id) throws  RestaurantNotFoundException;
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
    public void  deleteREstaurant(String id) throws RestaurantNotFoundException;
    public List<Dish> getDishItems(String id) throws RestaurantNotFoundException, DishNotFoundException;

}
