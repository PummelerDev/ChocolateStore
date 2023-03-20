package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.getCustomerById(id);
    }

    public void createCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
    }

    public void updateById(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public void deleteById(long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customerRepository.deleteCustomerById(customer);
    }
}
