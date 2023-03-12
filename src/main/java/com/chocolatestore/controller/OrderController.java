package com.chocolatestore.controller;

import com.chocolatestore.domain.Order;
import com.chocolatestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, order.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createOrder(@RequestBody Order order) {
        int result = orderService.createOrder(order);
        return new ResponseEntity<>(result > 0 ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateOrderById(@RequestBody Order order) {
        int result = orderService.updateOrderById(order);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable long id) {
        int result = orderService.deleteOrderById(id);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
