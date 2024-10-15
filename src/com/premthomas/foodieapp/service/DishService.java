package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.DishExistsException;
import com.premthomas.foodieapp.exceptions.DishNotFoundException;
import com.premthomas.foodieapp.model.Dish;

import java.util.List;

public interface DishService {
  public List<Dish> getDishesList();

    public Dish save(Dish dish) throws DishExistsException;
    public Dish getDishById(String id) throws DishNotFoundException;
    public Dish update(Dish dish) throws DishNotFoundException;
    public void delete(String id) throws DishNotFoundException;


}
