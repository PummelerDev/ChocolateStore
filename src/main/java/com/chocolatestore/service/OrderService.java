package com.chocolatestore.service;

import com.chocolatestore.domain.Order;
import com.chocolatestore.repository.OrderRepository;
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

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ArrayList<Order> getAllOrders() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrderById(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }
}