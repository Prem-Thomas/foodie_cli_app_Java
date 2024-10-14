package com.premthomas.foodieapp.repository;

import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {
    public List<Restaurant> restaurantsList;

    public RestaurantRepository(){
        this.restaurantsList = Factory.getCSvReader().readRestaurantFromCSv();
    }

    public Restaurant saveRestaurant(Restaurant restaurant){
        this.restaurantsList.add(restaurant);
        return restaurant;
    }

    public Restaurant updateResstaurant(Restaurant restaurantToBeUpdated){
        Optional<Restaurant> restaurantOptional = this.restaurantsList.stream().filter(restaurant -> restaurant.getId().equals(restaurantToBeUpdated.getId()))
                .findFirst()
                .map(restaurant -> {
                    restaurant.setName(restaurantToBeUpdated.getName())
                            .setAddress(restaurantToBeUpdated.getAddress())
                            .setMenu(restaurantToBeUpdated.getMenu());
                    return restaurant;
                });
        return restaurantOptional.orElse(null);
    }

    public List<Restaurant> getResaurantList(){
        return this.restaurantsList;
    }

    public void deleteRestaurant(Restaurant restaurant){
        this.restaurantsList.remove(restaurant);
    }
}
