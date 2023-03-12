package com.chocolatestore.mappers;

import com.chocolatestore.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        customer.setPurchaseAmount(rs.getDouble("purchase_amount"));
        customer.setLogin(rs.getString("login"));
        customer.setPassword(rs.getString("password"));
        customer.setCreated(rs.getTimestamp("created"));
        customer.setChanged(rs.getTimestamp("changed"));
        customer.setDeleted(rs.getBoolean("is_deleted"));
        return customer;
    }
}
