package com.premthomas.foodieapp.repository;

import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Dish;

import java.util.List;
import java.util.Optional;

public class DishRepository {
    List<Dish> dishList;
    public DishRepository(){
        this.dishList = Factory.getCSvReader().readDishesFromCsv();
    }

    public Dish saveDish(Dish dish){
        this.dishList.add(dish);
        return dish;
    }

    public Dish updateDish(Dish dishToBeUpdated){
        Optional<Dish> dishOptional = this.dishList.stream().filter(dish -> dish.getId().equals(dishToBeUpdated.getId()))
                .findFirst()
                .map(dish -> {
                    dish.setName(dishToBeUpdated.getName())
                            .setPrice(dishToBeUpdated.getPrice())
                            .setDescription(dishToBeUpdated.getDescription());
                    return dish;
                });
        return dishOptional.orElse(null);
    }

    public Optional<Dish> fidyDishById(String id){
        return this.dishList.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }

    public List<Dish> getDishList(){
        return this.dishList;
    }

    public void deleteDish(Dish dish){
        this.dishList.remove(dish);
    }
}
