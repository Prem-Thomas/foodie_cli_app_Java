package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.OrderExistsException;
import com.premthomas.foodieapp.exceptions.OrderNotFoundException;
import com.premthomas.foodieapp.model.Order1;
import com.premthomas.foodieapp.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order1> getOrdersList() {
        return this.orderRepository.getOrderList();
    }

    @Override
    public Order1 getOrderById(String id) throws OrderNotFoundException {
        Optional<Order1> orderById = this.orderRepository.findOrderById(id);
        if(orderById.isEmpty()) throw new OrderNotFoundException("Order Not Found woth Id : " + id);
        return orderById.get();
    }

    @Override
    public Order1 save(Order1 order) throws OrderExistsException {
        Optional<Order1> orderById = this.orderRepository.findOrderById(order.getId());
        if(orderById.isPresent()) throw  new OrderExistsException("Order is Already Exists with this Id : " + order.getId());

        return this.orderRepository.save(order);
    }
}
