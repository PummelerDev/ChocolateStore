package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import com.chocolatestore.mappers.CustomerMapper;
import com.chocolatestore.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;
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

    public ArrayList<CustomerDTO> getAllCustomers() {
        return (ArrayList<CustomerDTO>) customerRepository
                .findAll()
                .stream()
                .map(customerMapper::mapCustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(long id) {
        return customerMapper.mapCustomerToCustomerDTO(customerRepository.findById(id).get());
    }

    public Customer createCustomer(CustomerDTO cd) {
        return customerRepository.save(customerMapper.mapCustomerDTOToCustomer(cd));
    }

    @Transactional // TODO: 03.04.2023 transactional?
    public Customer updateById(long id, CustomerDTO cd) {
        Customer fromDB = customerRepository.findById(id).get();
        Customer intoDB = new Customer();
        intoDB.setId(id);
        intoDB.setFirstName(StringUtils.isBlank(cd.getFirstName()) ? fromDB.getFirstName() : cd.getFirstName());
        intoDB.setLastName(StringUtils.isBlank(cd.getLastName()) ? fromDB.getLastName() : cd.getLastName());
        intoDB.setAddress(StringUtils.isBlank(cd.getAddress()) ? fromDB.getAddress() : cd.getAddress());
        intoDB.setPhone(StringUtils.isBlank(cd.getPhone()) ? fromDB.getPhone() : cd.getPhone());
        intoDB.setEmail(StringUtils.isBlank(cd.getEmail()) ? fromDB.getEmail() : cd.getEmail());
        return customerRepository.saveAndFlush(intoDB);
    }

    public boolean deleteCustomerById(long id) {
        return customerRepository.deleteByIdCustom(id);
    }

    public boolean restoreCustomerById(long id) {
        return customerRepository.restoreByIdCustom(id);
    }

    public CustomerDTOLoginPassword getLoginAndPassword(long id) {
        return customerMapper.mapCustomerToCustomerDTOLoginPassword(customerRepository.findById(id).get());
    }

    public boolean updateLoginAndPassword(long id, CustomerDTOLoginPassword cdlp) {
        return customerRepository.updateLoginPassword(id, cdlp);
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