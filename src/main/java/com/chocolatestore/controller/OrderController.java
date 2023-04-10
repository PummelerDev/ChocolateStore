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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.ValidationException;
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

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTOResponseByNumber>> getAllOrdersOfCurrentCustomer(@RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        List<OrderDTOResponseByNumber> orders = orderService.getAllOrdersByNumberOfCurrentCustomer(login);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/all/admin")
    public ResponseEntity<List<Order>> getAllOrdersForAdmin() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDTOResponse> getOrderById(@PathVariable long id) {
        OrderDTOResponse o = orderService.getOrderById(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createOrder(@RequestBody @Valid OrderDTORequestCreate odr, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.toString());
        }
        Order o = orderService.createOrder(odr);
        if (o != null && o.getOrderNumber() >= 0) {
            return new ResponseEntity<>(o.getOrderNumber(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/add/{orderNumber}")
    public ResponseEntity<HttpStatus> addToOrderByOrderNumber(@PathVariable long orderNumber, @RequestBody @Valid OrderDTORequestAddOrUpdate odrau, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.toString());
        }
        Order o = orderService.addToOrderByOrderNumber(orderNumber, odrau);
        return new ResponseEntity<>(o != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{key}")
    public ResponseEntity<HttpStatus> updateOrderByKey(@PathVariable long key, @RequestBody @Valid OrderDTORequestAddOrUpdate odrau, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.toString());
        }
        Order o = orderService.updateOrderById(key, odrau);
        return new ResponseEntity<>(o != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/get/{id}/remove")
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
    public ResponseEntity<OrderDTOResponseByNumber> getOrderByNumberCurrentCustomer(@PathVariable long number, @RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        if (!orderService.toCheckCustomerAndOrderNumber(number, login)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        OrderDTOResponseByNumber odrn = orderService.getOrderDTOResponseByNumber(number);
        return new ResponseEntity<>(odrn, HttpStatus.OK);
    }

    @GetMapping("/number/{number}/admin")
    public ResponseEntity<OrderDTOResponseByNumber> getOrderByNumber(@PathVariable long number) {
        OrderDTOResponseByNumber odrn = orderService.getOrderDTOResponseByNumber(number);
        return new ResponseEntity<>(odrn, HttpStatus.OK);
    }

    @GetMapping("/number/{number}/pdf")
    public ResponseEntity<byte[]> getOrderPdfByOrderNumberForCurrentCustomer(@PathVariable Long number, @RequestHeader String authorization) {
        String login = jwtProvider.getLoginFromJwt(authorization.substring(7));
        if (!orderService.toCheckCustomerAndOrderNumber(number, login)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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

    @GetMapping("/number/{number}/pdf/admin")
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