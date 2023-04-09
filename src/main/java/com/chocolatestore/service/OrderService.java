package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.OrderDTORequestAddOrUpdate;
import com.chocolatestore.domain.DTO.OrderDTORequestCreate;
import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.DTO.OrderDTOResponseByNumber;
import com.chocolatestore.domain.Order;
import com.chocolatestore.mappers.OrderMapper;
import com.chocolatestore.repository.OrderRepository;
import com.chocolatestore.utils.PdfCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public ArrayList<OrderDTOResponseByNumber> getAllOrdersByNumberOfCurrentCustomer(String login) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAllByCustomersLogin(login);
        ArrayList<OrderDTOResponseByNumber> result = new ArrayList<>();
        HashMap<Long, ArrayList<Order>> ordersMap = new HashMap<>();
        Long key = 0l;
        Order value;
        for (int i = 0; i < orders.size(); i++) {
            key = orders.get(i).getOrderNumber();
            value = orders.get(i);
            if (!ordersMap.containsKey(key)) {
                ordersMap.put(key, new ArrayList<>());
                ordersMap.get(key).add(value);
            } else if (ordersMap.containsKey(key) && !ordersMap.get(key).contains(value)) {
                ordersMap.get(key).add(value);
            }
        }
        for (Map.Entry<Long, ArrayList<Order>> orderEntry :
                ordersMap.entrySet()) {
            result.add(
                    orderMapper.mapOrderToOrderDTOResponseByNumber(
                            orderEntry.getValue()
                                    .stream()
                                    .collect(Collectors.toList())));
        }
        return result;
    }

    public ArrayList<Order> getAllOrders() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public OrderDTOResponse getOrderById(long id) {
        return orderMapper.mapOrderToOrderDTOResponse(orderRepository.findById(id).get());
    }

    public Order createOrder(OrderDTORequestCreate o) {
        return orderRepository.saveCustom(o, createOrderId());
    }

    public Order addToOrderByOrderNumber(Long orderNumber, OrderDTORequestAddOrUpdate o) {
        return orderRepository.addByOrderNumber(o, orderNumber);
    }

    public Order updateOrderById(long id, OrderDTORequestAddOrUpdate o) {
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

    public OrderDTOResponseByNumber getOrderDTOResponseByNumber(long orderNumber) {
        return orderMapper.mapOrderToOrderDTOResponseByNumber(orderRepository.findAllByOrderNumber(orderNumber));
    }

    public ArrayList<Order> findAllByOrderNumber(long orderNumber) {
        orderRepository.findAllByOrderNumber(orderNumber);
        return (ArrayList<Order>) orderRepository.findAllByOrderNumber(orderNumber);
    }

    public byte[] createPdfFromOrderDtoResponse(Long number) {
        PdfCreator pdfCreator = new PdfCreator();
        return pdfCreator.createPdfFromOrderDtoResponse(getOrderDTOResponseByNumber(number));
    }

    private long createOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String value = localDateTime.format(formatter) + String.valueOf(localDateTime.getNano()).substring(0, 7);
        return Long.parseLong(value);
    }

    public boolean toCheckCustomerAndOrderNumber(Long number, String login) {
        ArrayList<Order> orders = findAllByOrderNumber(number);
        if (orders.get(0) == null | orders.get(0).getCustomer().getLogin() != login) {
            return true;
        }
        return false;
    }
}