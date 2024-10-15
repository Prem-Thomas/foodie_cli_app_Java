package com.premthomas.foodieapp.service;

import com.premthomas.foodieapp.exceptions.CustomerExistsException;
import com.premthomas.foodieapp.exceptions.CustomerNotFoundException;
import com.premthomas.foodieapp.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer save(Customer customer) throws CustomerExistsException;

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(String id) throws CustomerNotFoundException;

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

    public void deleteCustomer(String id) throws CustomerNotFoundException;

    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException;

    public void setCurrectLoggedInCustomer(Customer customer);

    public Customer getCurrectLoggedCustomer();


}
