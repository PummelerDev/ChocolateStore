package com.chocolatestore.controller;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, customer.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public ResponseEntity<HttpStatus> createCustomer(@ModelAttribute Customer customer) {
        int result = customerService.createCustomer(customer);
        return new ResponseEntity<>(result > 0 ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping// TODO: 27.02.2023 should i add an @ResponseBody? test it!
    @ResponseBody
    public ResponseEntity<HttpStatus> updateCustomerById(@ModelAttribute Customer customer) {
        int result = customerService.updateById(customer);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")// TODO: 27.02.2023 should i add an @ResponseBody? test it!
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable long id) {
        int result = customerService.deleteById(id);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
