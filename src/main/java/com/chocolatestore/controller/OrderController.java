package com.chocolatestore.controller;

import com.chocolatestore.domain.Order;
import com.chocolatestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(ModelMap modelMap) {
        List<Order> orders = orderService.getAllOrders();
        modelMap.addAttribute("orders", orders);
        return "allOrders";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable long id, ModelMap modelMap) {
        Order order = orderService.getOrderById(id);
        modelMap.addAttribute("order", order);
        return "order";
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public String createOrder(@ModelAttribute Order order) {
        int result = orderService.createOrder(order);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @PutMapping
    @ResponseBody
    public String updateOrderById(@ModelAttribute Order order) {
        int result = orderService.updateOrderById(order);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteOrderById(@PathVariable long id) {
        int result = orderService.deleteOrderById(id);
        return result > 0 ? "successful" : "unsuccessful";
    }
}
