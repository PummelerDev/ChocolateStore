package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ArrayList<Customer> getAllCustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).get();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateById(Customer customer) {
        return customerRepository.saveAndFlush(customer);
//        int result = 0;
//        try {
//            Customer theSameCustomerFromDB = getCustomerById(customer.getId());
//            result = jdbcTemplate.update(
//                    "update customers set first_name=?, last_name=?, address=?, phone=?, email=?, purchase_amount=?, login=?, password=?, changed=default, is_deleted=? where id=?",
//                    StringUtils.isBlank(customer.getFirstName()) ? theSameCustomerFromDB.getFirstName() : customer.getFirstName(),
//                    StringUtils.isBlank(customer.getLastName()) ? theSameCustomerFromDB.getLastName() : customer.getLastName(),
//                    StringUtils.isBlank(customer.getAddress()) ? theSameCustomerFromDB.getAddress() : customer.getAddress(),
//                    StringUtils.isBlank(customer.getPhone()) ? theSameCustomerFromDB.getPhone() : customer.getPhone(),
//                    StringUtils.isBlank(customer.getEmail()) ? theSameCustomerFromDB.getEmail() : customer.getEmail(),
//                    customer.getPurchaseAmount() == 0 ? theSameCustomerFromDB.getPurchaseAmount() : customer.getPurchaseAmount(),
//                    StringUtils.isBlank(customer.getLogin()) ? theSameCustomerFromDB.getLogin() : customer.getLogin(),
//                    StringUtils.isBlank(customer.getPassword()) ? theSameCustomerFromDB.getPassword() : customer.getPassword(),
//                    !customer.isDeleted() ? theSameCustomerFromDB.isDeleted() : customer.isDeleted(),
//                    customer.getId()
//            );
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        } finally {
//            return result;
//        }
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }
}