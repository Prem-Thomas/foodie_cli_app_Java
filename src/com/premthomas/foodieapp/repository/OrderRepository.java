package com.premthomas.foodieapp.repository;

import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Order1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    List<Order1> orderList;

    public OrderRepository(){
        this.orderList = new ArrayList<>();
    }

    public Order1 save(Order1 order){
        this.orderList.add(order);
        return order;
    }

    public List<Order1> getOrderList(){
        return this.orderList;
    }

    public Optional<Order1> findOrderById(String id){
        return this.orderList.stream().filter(order1 -> order1.getId().equals(id)).findFirst();
    }
}
