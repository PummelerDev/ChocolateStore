package com.chocolatestore.service;

import com.chocolatestore.domain.Order;
import com.chocolatestore.exceptions.OrderNotFoundException;
import com.chocolatestore.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class OrderService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            orders = (ArrayList<Order>) jdbcTemplate.query(
                    "select * from orders",
                    new OrderMapper()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return orders;
        }
    }

    public Order getOrderById(long id) {
        Order order = new Order();
        try {
            order = jdbcTemplate.queryForObject(
                    "select * from orders where id =?",
                    new OrderMapper(),
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return order;
        }
    }

    public int createOrder(Order order) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "insert into orders(id, order_number, product_id, customer_id, quantity, created, changed, canceled, finished) values(default, ?, ?, ?, ?, default, default, default, default)",
                    createOrderId(),
                    order.getProductId(),
                    order.getCustomerId(),
                    order.getQuantity()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int updateOrderById(Order order) {
        int result = 0;
        try {
            Order theSameOrderFromDB = getOrderById(order.getId());
            result = jdbcTemplate.update(
                    "update orders set product_id=?, customer_id=?, quantity=?, changed=default, canceled=?, finished=? where id=?",
                    order.getProductId() == 0 ? theSameOrderFromDB.getProductId() : order.getProductId(),
                    order.getCustomerId() == 0 ? theSameOrderFromDB.getCustomerId() : order.getCustomerId(),
                    order.getQuantity() == 0 ? theSameOrderFromDB.getQuantity() : order.getQuantity(),
                    !order.isCancelled() ? theSameOrderFromDB.isCancelled() : order.isCancelled(),
                    !order.isFinished() ? theSameOrderFromDB.isFinished() : order.isFinished()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int deleteOrderById(long id) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "delete from orders where id=?",
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }
}