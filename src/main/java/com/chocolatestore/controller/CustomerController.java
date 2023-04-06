package com.chocolatestore.controller;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import com.chocolatestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customersDTO = customerService.getAllCustomers();
        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable long id) {
        CustomerDTO cd = customerService.getCustomerById(id);
        return new ResponseEntity<>(cd, cd != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody @Valid CustomerDTO cd, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Customer c = customerService.createCustomer(cd);
        return new ResponseEntity<>(c != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateCustomerById(@PathVariable long id, @RequestBody @Valid CustomerDTO cd, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Customer c = customerService.updateById(id, cd);
        // TODO: 03.04.2023 how to check?
        return new ResponseEntity<>(c != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable long id) {
        boolean result = customerService.deleteCustomerById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}/restore")
    public ResponseEntity<HttpStatus> restoreCustomerById(@PathVariable long id) {
        boolean result = customerService.restoreCustomerById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> removeCustomerById(@PathVariable long id) {
        boolean result = customerService.removeCustomerById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<CustomerDTOLoginPassword> getLoginAndPassword(@PathVariable Long id){
        CustomerDTOLoginPassword cdlp =customerService.getLoginAndPassword(id);
        return new ResponseEntity<>(cdlp, !cdlp.getLogin().isBlank() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @PutMapping("/login/{id}")
    public ResponseEntity<CustomerDTOLoginPassword> updateLoginAndPassword(@PathVariable Long id, @RequestBody CustomerDTOLoginPassword cdlp){
        boolean result =customerService.updateLoginAndPassword(id, cdlp);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}