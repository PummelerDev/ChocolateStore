package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class CustomerService {

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customersList = new ArrayList<>();
        try {
            Connection connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
                    );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from customers"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customerMapping(customer, resultSet);
                customersList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customersList;
    }

    public Customer getCustomerById(long id) {
        Customer customer = new Customer();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from customers where id=?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            customerMapping(customer, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public int createCustomer(Customer customer) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into customers(id, first_name, last_name, address, phone, email, purchase_amount, login, password, created, changed, is_deleted) values (default, ?, ?, ?, ?, ?, default, ?, ?, default, default, default )"
            );
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getLogin());
            preparedStatement.setString(7, customer.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 26.02.2023 can i use customer like a param? or should i use fields from customer?
    public int updateById(Customer customer) {
        int result = 0;
        try {
            Customer theSameCustomerFromDB = getCustomerById(customer.getId());
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update customers set first_name=?, last_name=?, address=?, phone=?, email=?, purchase_amount=?, login=?, password=?, changed=default, is_deleted=? where id=?"
            );
            preparedStatement.setString(1, StringUtils.isBlank(customer.getFirstName()) ? theSameCustomerFromDB.getFirstName() : customer.getFirstName());
            preparedStatement.setString(2, StringUtils.isBlank(customer.getLastName()) ? theSameCustomerFromDB.getLastName() : customer.getLastName());
            preparedStatement.setString(3, StringUtils.isBlank(customer.getAddress()) ? theSameCustomerFromDB.getAddress() : customer.getAddress());
            preparedStatement.setString(4, StringUtils.isBlank(customer.getPhone()) ? theSameCustomerFromDB.getPhone() : customer.getPhone());
            preparedStatement.setString(5, StringUtils.isBlank(customer.getEmail()) ? theSameCustomerFromDB.getEmail() : customer.getEmail());
            preparedStatement.setDouble(6, customer.getPurchaseAmount() == 0 ? theSameCustomerFromDB.getPurchaseAmount() : customer.getPurchaseAmount());
            preparedStatement.setString(7, StringUtils.isBlank(customer.getLogin()) ? theSameCustomerFromDB.getLogin() : customer.getLogin());
            preparedStatement.setString(8, StringUtils.isBlank(customer.getPassword()) ? theSameCustomerFromDB.getPassword() : customer.getPassword());
            preparedStatement.setBoolean(9, customer.isDeleted() == false ? theSameCustomerFromDB.isDeleted() : customer.isDeleted());
            preparedStatement.setLong(10, customer.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(long id) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from customers where id=?"
            );
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void customerMapping(Customer customer, ResultSet resultSet) throws SQLException {
        customer.setId(resultSet.getLong("id"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setAddress(resultSet.getString("address"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPurchaseAmount(resultSet.getDouble("purchase_amount"));
        customer.setLogin(resultSet.getString("login"));
        customer.setPassword(resultSet.getString("password"));
        customer.setCreated(resultSet.getTimestamp("created"));
        customer.setChanged(resultSet.getTimestamp("changed"));
        customer.setDeleted(resultSet.getBoolean("is_deleted"));
    }
}
