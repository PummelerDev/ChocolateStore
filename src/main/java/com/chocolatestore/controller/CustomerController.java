package com.chocolatestore.controller;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllCustomers(ModelMap modelMap) {
        List<Customer> customers = customerService.getAllCustomers();
        modelMap.addAttribute("customers", customers);
        return "allCustomers";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable long id, ModelMap modelMap) {
        Customer customer = customerService.getCustomerById(id);
        modelMap.addAttribute("customer", customer);
        return "customer";
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        int result = customerService.createCustomer(customer);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @PutMapping// TODO: 27.02.2023 should i add an @ResponseBody? test it!
    @ResponseBody
    public String updateCustomerById(@ModelAttribute Customer customer) {
        int result = customerService.updateById(customer);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @DeleteMapping("/{id}")// TODO: 27.02.2023 should i add an @ResponseBody? test it!
    @ResponseBody
    public String deleteCustomerById(@PathVariable long id) {
        int result = customerService.deleteById(id);
        return result > 0 ? "successful" : "unsuccessful";
    }
}
