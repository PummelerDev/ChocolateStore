package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.exceptions.CustomerNotFoundException;
import com.chocolatestore.mappers.CustomerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;

@Service
public class CustomerService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customersList = new ArrayList<>();
        try {
            customersList = (ArrayList<Customer>) jdbcTemplate.query(
                    "select * from customers",
                    new CustomerMapper()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    public Customer getCustomerById(long id) {
        Customer customer = new Customer();
        try {
            customer = jdbcTemplate.queryForObject(
                    "select * from customers where id=?",
                    new CustomerMapper(), id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (customer.getId() <= 0) {
            throw new CustomerNotFoundException("there is no customer with id " + id);
        }
        return customer;
    }

    public int createCustomer(Customer customer) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "insert into customers(id, first_name, last_name, address, phone, email, purchase_amount, login, password, created, changed, is_deleted) values (default, ?, ?, ?, ?, ?, default, ?, ?, default, default, default )",
                    customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getPhone(), customer.getEmail(), customer.getLogin(), customer.getPassword()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateById(Customer customer) {
        int result = 0;
        try {
            Customer theSameCustomerFromDB = getCustomerById(customer.getId());
            result = jdbcTemplate.update(
                    "update customers set first_name=?, last_name=?, address=?, phone=?, email=?, purchase_amount=?, login=?, password=?, changed=default, is_deleted=? where id=?",
                    StringUtils.isBlank(customer.getFirstName()) ? theSameCustomerFromDB.getFirstName() : customer.getFirstName(),
                    StringUtils.isBlank(customer.getLastName()) ? theSameCustomerFromDB.getLastName() : customer.getLastName(),
                    StringUtils.isBlank(customer.getAddress()) ? theSameCustomerFromDB.getAddress() : customer.getAddress(),
                    StringUtils.isBlank(customer.getPhone()) ? theSameCustomerFromDB.getPhone() : customer.getPhone(),
                    StringUtils.isBlank(customer.getEmail()) ? theSameCustomerFromDB.getEmail() : customer.getEmail(),
                    customer.getPurchaseAmount() == 0 ? theSameCustomerFromDB.getPurchaseAmount() : customer.getPurchaseAmount(),
                    StringUtils.isBlank(customer.getLogin()) ? theSameCustomerFromDB.getLogin() : customer.getLogin(),
                    StringUtils.isBlank(customer.getPassword()) ? theSameCustomerFromDB.getPassword() : customer.getPassword(),
                    !customer.isDeleted() ? theSameCustomerFromDB.isDeleted() : customer.isDeleted(),
                    customer.getId()
            );
        } catch (DataAccessException | CustomerNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(long id) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "delete from customers where id=?",
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
