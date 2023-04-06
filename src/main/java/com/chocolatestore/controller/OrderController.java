package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.OrderDTORequest;
import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.DTO.OrderDTOResponseByNumber;
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

    @PutMapping("/{id}") // TODO: 06.04.2023 add id! как выбрать какой-то конкретный заказ??
    public ResponseEntity<HttpStatus> updateOrderById(@PathVariable long id, @RequestBody OrderDTORequest o) {
        orderService.updateOrderById(id, o);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> removeOrderById(@PathVariable long id) {
        boolean result = orderService.removeOrderById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/cancel/{orderNumber}/{id}")
    public ResponseEntity<HttpStatus> cancelOrderByNumberAndId(@PathVariable long orderNumber, @PathVariable long id) {
        boolean result = orderService.cancelOrderByNumberAndId(orderNumber, id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/cancel/{orderNumber}")
    public ResponseEntity<HttpStatus> cancelAllOrdersByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.cancelAllOrdersByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/collect/{orderNumber}")
    public ResponseEntity<HttpStatus> collectOrderByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.collectOrderByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/finish/{orderNumber}")
    public ResponseEntity<HttpStatus> finishOrderByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.finishOrderByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<OrderDTOResponseByNumber> getOrderByNumber(@PathVariable long number) {
        OrderDTOResponseByNumber odrn = orderService.getOrderByNumber(number);
        return new ResponseEntity<>(odrn, odrn.getOrderNumber() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}