package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.exceptions.RestaurantExistsException;
import com.premthomas.foodieapp.exceptions.RestaurantNotFoundException;
import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Dish;
import com.premthomas.foodieapp.model.Restaurant;
import com.premthomas.foodieapp.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements  RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.getResaurantList();
    }

    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestuarantById(restaurant.getId());
        if(restaurantById.isPresent()) throw new RestaurantExistsException("Restaurant aalready Exists with this Id : " + restaurant.getId());
        return this.restaurantRepository.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {

       Optional<Restaurant> restaurantById = this.restaurantRepository.findRestuarantById(id);
      if(restaurantById.isEmpty()) throw new RestaurantNotFoundException("Restauramt Not Found with This Id : "+ id);
       return restaurantById.get();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestuarantById(restaurant.getId());
        if(restaurantById.isEmpty()) throw new  RestaurantNotFoundException("Restaurant Not Found with this Id : " + restaurant.getId());
        return this.restaurantRepository.updateResstaurant(restaurant);
    }

    @Override
    public void deleteREstaurant(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestuarantById(id);
        if(restaurantById.isEmpty()) throw new  RestaurantNotFoundException("Restaurant Not Found with this Id : " + id);
        this.restaurantRepository.deleteRestaurant(restaurantById.get());

    }

    @Override
    public List<Dish> getDishItems(String id) throws RestaurantNotFoundException, DishNotFoundException {
       Optional<Restaurant> restaurantById = this.restaurantRepository.findRestuarantById(id);
       if(restaurantById.isEmpty()) throw new RestaurantNotFoundException("Restaurant No Found with this Id :" + id );
       List<Dish> dishList = new ArrayList<>();
       Restaurant restaurant = restaurantById.get();
       List<String> dishIds = restaurant.getMenu();
       DishService dishService = Factory.getDishService();
       for(String dishId : dishIds){
           Dish dish = dishService.getDishById(dishId);
           dishList.add(dish);
       }
        return dishList;
    }
}
