package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.OrderDTORequest;
import com.chocolatestore.domain.DTO.OrderDTOResponse;
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
    public ResponseEntity<List<OrderDTOResponse>> getAllOrders() {
        List<OrderDTOResponse> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTOResponse> getOrderById(@PathVariable long id) {
        OrderDTOResponse o = orderService.getOrderById(id);
        return new ResponseEntity<>(o, o != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderDTORequest odr) {
        Order o = orderService.createOrder(odr);
        return new ResponseEntity<>(o != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOrderById(@PathVariable long id, @RequestBody OrderDTORequest o) {
        orderService.updateOrderById(id, o);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable long id) {
        boolean result = orderService.deleteOrderById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}