package com.premthomas.foodieapp.repository;

import com.premthomas.foodieapp.factory.Factory;
import com.premthomas.foodieapp.model.Customer;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    List<Customer> customerList;
  public CustomerRepository(){
      this.customerList = Factory.getCSvReader().readCustomersFromCsv();
  }
    public Customer saveCustomerList(Customer customer){
         this.customerList.add(customer);
         return customer;
    }
// Find the Customer Details by the Id
    public Optional<Customer> findCustomerById(String id){
        return this.customerList.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }
// Find the Customer Details by the Email
    public Optional<Customer> findCustomerByEmail(String email){
        return this.customerList.stream().filter(customer -> customer.getEmail().equals(email)).findFirst();
    }
  // For Update the Customer Details
    public Customer updateCustomer(Customer customerToBeUpdated){
        Optional<Customer> updateCustomer = this.customerList.stream().filter(customer -> customer.getId().equals(customerToBeUpdated))
                .findFirst()
                .map(customer -> {
                    customer.setName(customerToBeUpdated.getName())
                            .setEmail(customerToBeUpdated.getEmail())
                            .setPassword(customerToBeUpdated.getPassword());
                    return customer;
                });
        return updateCustomer.orElse(null);
    }
 // Get the values From the Customer List

    public List<Customer> getCustomerList(){
        return this.customerList;
    }

// To Delete the Customer When We don't need

    public void deleteCustomer(Customer customer){
        this.customerList.remove(customer);
    }

    // Find the Details of Customer By Email and Password
    public Optional<Customer> findByEmailAndPassword(String email,String password){
        return this.customerList.stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password)).findFirst();
    }
}
