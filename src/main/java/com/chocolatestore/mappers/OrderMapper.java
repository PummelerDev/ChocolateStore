package com.chocolatestore.mappers;

import com.chocolatestore.domain.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setOrderNumber(rs.getLong("order_number"));
        order.setProductId(rs.getLong("product_id"));
        order.setCustomerId(rs.getLong("customer_id"));
        order.setQuantity(rs.getInt("quantity"));
        order.setCreated(rs.getTimestamp("created"));
        order.setChanged(rs.getTimestamp("changed"));
        order.setCancelled(rs.getBoolean("cancelled"));
        order.setFinished(rs.getBoolean("finished"));
        return order;
    }
}
