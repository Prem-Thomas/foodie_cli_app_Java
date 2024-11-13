package com.premthomas.foodieapp.controller;

import com.premthomas.foodieapp.exceptions.DishExistsException;
import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.model.Dish;
import com.premthomas.foodieapp.service.DishServiceImpl;

import java.util.List;

public class DishController {
    private DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService){
        this.dishService = dishService;
    }

    public List<Dish> getDishList(){
        return this.dishService.getDishesList();
    }

    public Dish save(Dish dish) throws DishExistsException{
        return this.dishService.save(dish);
    }

    public Dish update(Dish dish) throws DishNotFoundException{
        return this.dishService.update(dish);
    }

    public Dish getDishById(String id) throws DishNotFoundException{
        return this.dishService.getDishById(id);
    }

    public void deleteDish(String id) throws DishNotFoundException{
        this.dishService.delete(id);
    }
}
