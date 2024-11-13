package com.premthomas.foodieapp.controller;

import com.premthomas.foodieapp.exceptions.OrderExistsException;
import com.premthomas.foodieapp.exceptions.OrderNotFoundException;
import com.premthomas.foodieapp.model.Order1;
import com.premthomas.foodieapp.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    public List<Order1> getOrdersList(){
        return this.orderService.getOrdersList();
    }

    public Order1 getOrderById(String id) throws OrderNotFoundException{
        return this.orderService.getOrderById(id);
    }

    public Order1 saveOrder(Order1 order) throws OrderExistsException{
        return this.orderService.save(order);
    }
}
