package com.premthomas.foodieapp.controller;

import com.premthomas.foodieapp.exceptions.CustomerExistsException;
import com.premthomas.foodieapp.exceptions.CustomerNotFoundException;
import com.premthomas.foodieapp.model.Customer;
import com.premthomas.foodieapp.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {

    private final CustomerServiceImpl customerService;
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerExistsException{
        return this.customerService.save(customer);
    }

    public  Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        Customer customer = this.customerService.validateCustomerLogin(email, password);
        if (customer != null)
            this.customerService.setCurrectLoggedInCustomer(customer);
            return customer;
    }

    public Customer currentLoggedInCustomer(){
        return this.customerService.getCurrectLoggedCustomer();
    }

    public Customer getCustomerById(String id) throws CustomerNotFoundException{
        return this.customerService.getCustomerById(id);
    }

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException{
        return this.customerService.updateCustomer(customer);
    }

    public List<Customer> getCustomerList(){
        return this.customerService.getAllCustomers();
    }
    public void deleteCustomer(String id) throws CustomerNotFoundException{
        this.customerService.deleteCustomer(id);
    }


}
