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
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(long id) {
        return orderRepository.getOrderById(id);
    }

    public void createOrder(Order order) {
        orderRepository.createOrder(order);
    }

    public void updateOrderById(Order order) {
        orderRepository.updateOrder(order);
    }

    public void deleteOrderById(long id) {
        Order order = new Order();
        order.setId(id);
        orderRepository.deleteOrderById(order);
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }
}