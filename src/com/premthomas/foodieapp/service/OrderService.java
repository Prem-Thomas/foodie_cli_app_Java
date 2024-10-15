package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.OrderExistsException;
import com.premthomas.foodieapp.exceptions.OrderNotFoundException;
import com.premthomas.foodieapp.model.Order1;

import java.util.List;

public interface OrderService {

     public List<Order1>  getOrdersList();
     public Order1 getOrderById(String id) throws OrderNotFoundException;
     public Order1 save(Order1 order) throws OrderExistsException;

}
