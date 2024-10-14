package com.premthomas.foodieapp.exceptions;

public class OrderExistsException extends Exception{
    public OrderExistsException(String message){
        super(message);
    }
}
