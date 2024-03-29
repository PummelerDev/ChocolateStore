package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOForUpdate;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import com.chocolatestore.exceptions.CustomerNotFoundException;
import com.chocolatestore.mappers.CustomerMapper;
import com.chocolatestore.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.findAll();
        if (customers.isEmpty()){
            throw new CustomerNotFoundException("Customers not found!");
        }
        return customers;
    }

    public CustomerDTO getCustomerById(long id) {
        return customerMapper.mapCustomerToCustomerDTO(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found!")));
    }

    public CustomerDTO getCustomerDTOByLogin(String login) {
        return customerMapper.mapCustomerToCustomerDTO(customerRepository.findCustomerByLogin(login).orElseThrow(() -> new CustomerNotFoundException("Customer with login " + login + " not found!")));
    }

    public Boolean updateByLogin(String login, CustomerDTOForUpdate cdu) {
        Customer fromDB = customerRepository.findCustomerByLogin(login).get();
        Customer intoDB = new Customer();
        intoDB.setId(fromDB.getId());
        intoDB.setFirstName(StringUtils.isBlank(cdu.getFirstName()) ? fromDB.getFirstName() : cdu.getFirstName());
        intoDB.setLastName(StringUtils.isBlank(cdu.getLastName()) ? fromDB.getLastName() : cdu.getLastName());
        intoDB.setAddress(StringUtils.isBlank(cdu.getAddress()) ? fromDB.getAddress() : cdu.getAddress());
        intoDB.setPhone(StringUtils.isBlank(cdu.getPhone()) ? fromDB.getPhone() : cdu.getPhone());
        intoDB.setEmail(StringUtils.isBlank(cdu.getEmail()) ? fromDB.getEmail() : cdu.getEmail());
        Optional<Customer> c = Optional.of(customerRepository.saveAndFlush(intoDB));
        return c.isPresent();
    }

    public boolean deleteCustomerByLogin(String login) {
        return customerRepository.deleteByLoginCustom(login);
    }

    public boolean restoreCustomerById(long id) {
        return customerRepository.restoreByIdCustom(id);
    }

    public CustomerDTOLoginPassword getLoginAndPassword(String login) {
        return customerMapper.mapCustomerToCustomerDTOLoginPassword(customerRepository.findCustomerByLogin(login).orElseThrow(() -> new CustomerNotFoundException("Customer with login " + login + " not found!")));
    }

    public boolean updateLoginAndPassword(String login, CustomerDTOLoginPassword cdlp) {
        return customerRepository.updateLoginPassword(login, cdlp);
    }

    public boolean removeCustomerById(long id) {
        customerRepository.deleteById(id);
        return !customerRepository.existsById(id);
    }

    public Customer getCustomerByLogin(String login) {
        return customerRepository.findCustomerByLogin(login).orElseThrow(() -> new CustomerNotFoundException("Customer with login " + login + " not found!"));
    }

    public String getRole(long id) {
        return customerRepository.getRole(id);
    }
}