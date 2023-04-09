package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
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
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public CustomerDTO getCustomerById(long id) {
        return customerMapper.mapCustomerToCustomerDTO(customerRepository.findById(id).get());
    }

    public CustomerDTO getCustomerDTOByLogin(String login) {
        return customerMapper.mapCustomerToCustomerDTO(customerRepository.findCustomerByLogin(login).get());
    }

    public Customer createCustomer(CustomerDTO cd) {
        return customerRepository.save(customerMapper.mapCustomerDTOToCustomer(cd));
    }

    public Boolean updateById(String login, CustomerDTO cd) {
        Customer fromDB = customerRepository.findCustomerByLogin(login).get();
        Customer intoDB = new Customer();
        intoDB.setId(fromDB.getId());
        intoDB.setFirstName(StringUtils.isBlank(cd.getFirstName()) ? fromDB.getFirstName() : cd.getFirstName());
        intoDB.setLastName(StringUtils.isBlank(cd.getLastName()) ? fromDB.getLastName() : cd.getLastName());
        intoDB.setAddress(StringUtils.isBlank(cd.getAddress()) ? fromDB.getAddress() : cd.getAddress());
        intoDB.setPhone(StringUtils.isBlank(cd.getPhone()) ? fromDB.getPhone() : cd.getPhone());
        intoDB.setEmail(StringUtils.isBlank(cd.getEmail()) ? fromDB.getEmail() : cd.getEmail());
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
        return customerMapper.mapCustomerToCustomerDTOLoginPassword(customerRepository.findCustomerByLogin(login).get());
    }

    public boolean updateLoginAndPassword(String login, CustomerDTOLoginPassword cdlp) {
        return customerRepository.updateLoginPassword(login, cdlp);
    }

    public boolean removeCustomerById(long id) {
        customerRepository.deleteById(id);
        return !customerRepository.existsById(id);
    }

    public Optional<Customer> getCustomerByLogin(String login) {
        return customerRepository.findCustomerByLogin(login);
    }

    public String getRole(long id) {
        return customerRepository.getRole(id);
    }
}