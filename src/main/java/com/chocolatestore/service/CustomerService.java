package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.exceptions.CustomerNotFoundException;
import com.chocolatestore.mappers.CustomerMapper;
import com.chocolatestore.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customersList = new ArrayList<>();
        customersList = customerRepository.getAllCustomers();
        return customersList;
    }

    public Customer getCustomerById(long id) {
        Customer customer = customerRepository.getCustomerById(id);
        return customer;
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
