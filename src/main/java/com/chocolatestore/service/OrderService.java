package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.OrderDTORequest;
import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.Order;
import com.chocolatestore.mappers.OrderMapper;
import com.chocolatestore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public ArrayList<OrderDTOResponse> getAllOrders() {
        return (ArrayList<OrderDTOResponse>) orderRepository
                .findAll()
                .stream()
                .map(orderMapper::mapOrderToOrderDTOResponse)
                .collect(Collectors.toList());
    }

    public OrderDTOResponse getOrderById(long id) {
        return orderMapper.mapOrderToOrderDTOResponse(orderRepository.findById(id).get());
    }

    public Order createOrder(OrderDTORequest o) {
        return orderRepository.save(o, createOrderId());
    }

    public Order updateOrderById(long id, OrderDTORequest o) {
        return orderRepository.saveAndFlushCustom(id, o);
    }

    public boolean removeOrderById(long id) {
        orderRepository.deleteById(id);
        return !orderRepository.existsById(id);
    }

    public boolean cancelOrderByNumberAndId(long orderNumber, long id) {
       return orderRepository.cancelOrderByNumberAndId(orderNumber, id);
    }

    public boolean cancelAllOrdersByNumber(long orderNumber) {
        return orderRepository.cancelAllOrdersByNumber(orderNumber);
    }

    public boolean collectOrderByNumber(long orderNumber) {
        return orderRepository.collectOrderByNumber(orderNumber);
    }

    public boolean finishOrderByNumber(long orderNumber) {
        return orderRepository.finishOrderByNumber(orderNumber);
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }
}