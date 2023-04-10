package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.OrderDTORequestAddOrUpdate;
import com.chocolatestore.domain.DTO.OrderDTORequestCreate;
import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.DTO.OrderDTOResponseByNumber;
import com.chocolatestore.domain.Order;
import com.chocolatestore.exceptions.OrderNotFoundException;
import com.chocolatestore.mappers.OrderMapper;
import com.chocolatestore.repository.CustomerRepository;
import com.chocolatestore.repository.OrderRepository;
import com.chocolatestore.utils.PdfCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    public ArrayList<OrderDTOResponseByNumber> getAllOrdersByNumberOfCurrentCustomer(String login) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAllByCustomersLogin(login);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("Orders not found!");
        }
        ArrayList<OrderDTOResponseByNumber> result = new ArrayList<>();
        HashMap<Long, ArrayList<Order>> ordersMap = new HashMap<>();
        Long key;
        Order value;
        for (Order order : orders) {
            key = order.getOrderNumber();
            value = order;
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
                            new ArrayList<>(orderEntry.getValue())));
        }
        return result;
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("Orders not found!");
        }
        return orders;
    }

    public OrderDTOResponse getOrderById(long id) {
        return orderMapper.mapOrderToOrderDTOResponse(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + "not found!")));
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

    @Transactional
    public boolean finishOrderByNumber(long orderNumber) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAllByOrderNumber(orderNumber);
        if (orders.isEmpty()){
            throw new OrderNotFoundException("Order with number " + orderNumber + " not found!");
        }
        OrderDTOResponseByNumber odrn = orderMapper.mapOrderToOrderDTOResponseByNumber(orders);
        boolean result = customerRepository.updatePurchaseAmountById(orders.get(0).getCustomer().getId(),odrn.getTotalPrice());
        if (!result){
            throw new RuntimeException();
        }
        return orderRepository.finishOrderByNumber(orderNumber);
    }

    public OrderDTOResponseByNumber getOrderDTOResponseByNumber(long orderNumber) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAllByOrderNumber(orderNumber);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("Orders not found!");
        }
        return orderMapper.mapOrderToOrderDTOResponseByNumber(orders);
    }

    public ArrayList<Order> findAllByOrderNumber(long orderNumber) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAllByOrderNumber(orderNumber);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("Orders not found!");
        }
        return orders;
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