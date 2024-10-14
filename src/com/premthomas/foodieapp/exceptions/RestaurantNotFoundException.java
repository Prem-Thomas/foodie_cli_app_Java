package com.premthomas.foodieapp.exceptions;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
