package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.*;
import com.chocolatestore.domain.Order;
import com.chocolatestore.security.JWT.JwtProvider;
import com.chocolatestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final JwtProvider jwtProvider;

    @Autowired
    public OrderController(OrderService orderService, JwtProvider jwtProvider) {
        this.orderService = orderService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/all") // TODO: 09.04.2023 all
    public ResponseEntity<List<OrderDTOResponseByNumber>> getAllOrdersOfCurrentCustomer(@RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        List<OrderDTOResponseByNumber> orders = orderService.getAllOrdersByNumberOfCurrentCustomer(login);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/all/admin") // TODO: 09.04.2023 admin
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get/{id}") // TODO: 09.04.2023 admin
    public ResponseEntity<OrderDTOResponse> getOrderById(@PathVariable long id) {
        OrderDTOResponse o = orderService.getOrderById(id);
        return new ResponseEntity<>(o, o != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/create") // TODO: 09.04.2023 all
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTORequestCreate odr) {
        Order o = orderService.createOrder(odr);
        if (o != null && o.getOrderNumber() >= 0) {
            return new ResponseEntity<>(o.getOrderNumber(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/add/{orderNumber}") // TODO: 09.04.2023 all
    public ResponseEntity<HttpStatus> addToOrderByOrderNumber(@PathVariable long orderNumber, @RequestBody OrderDTORequestAddOrUpdate odrau) {
        Order o = orderService.addToOrderByOrderNumber(orderNumber, odrau);
        return new ResponseEntity<>(o != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{key}") // TODO: 09.04.2023 all
    public ResponseEntity<HttpStatus> updateOrderByKey(@PathVariable long key, @RequestBody OrderDTORequestAddOrUpdate odrau) {
        orderService.updateOrderById(key, odrau);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/get/{id}/remove") // TODO: 09.04.2023 ??? CHECK IT! admin
    public ResponseEntity<HttpStatus> removeOrderById(@PathVariable long id) {
        boolean result = orderService.removeOrderById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/cancel/{orderNumber}/{id}") // TODO: 09.04.2023 all  CHECK IT id???
    public ResponseEntity<HttpStatus> cancelOrderByNumberAndId(@PathVariable long orderNumber, @PathVariable long id) {
        boolean result = orderService.cancelOrderByNumberAndId(orderNumber, id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/cancel/{orderNumber}") // TODO: 09.04.2023 all
    public ResponseEntity<HttpStatus> cancelAllOrdersByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.cancelAllOrdersByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/collect/{orderNumber}") // TODO: 09.04.2023 all
    public ResponseEntity<HttpStatus> collectOrderByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.collectOrderByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PostMapping("/finish/{orderNumber}") // TODO: 09.04.2023 admin
    public ResponseEntity<HttpStatus> finishOrderByNumber(@PathVariable long orderNumber) {
        boolean result = orderService.finishOrderByNumber(orderNumber);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @GetMapping("/number/{number}") // TODO: 09.04.2023 all
    public ResponseEntity<OrderDTOResponseByNumber> getOrderByNumberCurrentCustomer(@PathVariable long number, @RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        if (orderService.toCheckCustomerAndOrderNumber(number, login)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        OrderDTOResponseByNumber odrn = orderService.getOrderDTOResponseByNumber(number);
        return new ResponseEntity<>(odrn, odrn.getOrderNumber() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/number/{number}/admin") // TODO: 09.04.2023 all
    public ResponseEntity<OrderDTOResponseByNumber> getOrderByNumber(@PathVariable long number) {
        OrderDTOResponseByNumber odrn = orderService.getOrderDTOResponseByNumber(number);
        return new ResponseEntity<>(odrn, odrn.getOrderNumber() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/number/{number}/pdf") // TODO: 09.04.2023 all
    public ResponseEntity<byte[]> getOrderPdfByOrderNumberForCurrentCustomer(@PathVariable Long number, @RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        if (orderService.toCheckCustomerAndOrderNumber(number, login)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        byte[] result = orderService.createPdfFromOrderDtoResponse(number);
        if (result.length == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order_" + number + ".pdf")
                .body(result);
    }

    @GetMapping("/number/{number}/pdf/admin") // TODO: 09.04.2023 admin
    public ResponseEntity<byte[]> getOrderPdfByOrderNumber(@PathVariable Long number) {
        byte[] result = orderService.createPdfFromOrderDtoResponse(number);
        if (result.length == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order_" + number + ".pdf")
                .body(result);
    }
}