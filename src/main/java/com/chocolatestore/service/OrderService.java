package com.chocolatestore.service;

import com.chocolatestore.domain.Order;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class OrderService {

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from orders"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                orderMapping(order, resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderById(long id) {
        Order order = new Order();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from orders where id =?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            orderMapping(order, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // TODO: 26.02.2023 can i use order like a param? or should i use fields from order?
    // TODO: 26.02.2023 how should i send product_id and customer_id?
    public int createOrder(Order order) {
        int value = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into orders(id, order_number, product_id, customer_id, quantity, created, changed, canceled, finished) values(default, ?, ?, ?, ?, default, default, default, default)"
            );
            preparedStatement.setLong(1, createOrderId());
            preparedStatement.setLong(2, order.getProductId());
            preparedStatement.setLong(3, order.getCustomerId());
            preparedStatement.setLong(4, order.getQuantity());
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    // TODO: 26.02.2023 can i use order like a param? or should i use fields from order?
    public int updateOrderById(Order order) {
        int value = 0;
        try {
            Order theSameOrderFromDB = getOrderById(order.getId());
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update orders set product_id=?, customer_id=?, quantity=?, changed=default, canceled=?, finished=? where id=?"
            );
            preparedStatement.setLong(1, order.getProductId() == 0 ? theSameOrderFromDB.getProductId() : order.getProductId());
            preparedStatement.setLong(2, order.getCustomerId() == 0 ? theSameOrderFromDB.getCustomerId() : order.getCustomerId());
            preparedStatement.setInt(3, order.getQuantity() == 0 ? theSameOrderFromDB.getQuantity() : order.getQuantity());
            preparedStatement.setBoolean(4, order.isCancelled()==false? theSameOrderFromDB.isCancelled() : order.isCancelled());
            preparedStatement.setBoolean(5, order.isFinished()==false? theSameOrderFromDB.isFinished(): order.isFinished());
            preparedStatement.setLong(6, order.getId());
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int deleteOrderById(long id) {
        int value = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from orders where id=?"
            );
            preparedStatement.setLong(1, id);
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    private void orderMapping(Order order, ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getLong("id"));
        order.setOrderNumber(resultSet.getLong("order_number"));
        order.setProductId(resultSet.getLong("product_id"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setCreated(resultSet.getTimestamp("created"));
        order.setChanged(resultSet.getTimestamp("changed"));
        order.setCancelled(resultSet.getBoolean("cancelled"));
        order.setFinished(resultSet.getBoolean("finished"));
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }
}
