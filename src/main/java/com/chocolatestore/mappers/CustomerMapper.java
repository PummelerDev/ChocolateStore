package com.chocolatestore.mappers;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTO;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapCustomerDTOToCustomer(CustomerDTO cd) {
        Customer c = new Customer();
        c.setFirstName(cd.getFirstName());
        c.setLastName(cd.getLastName());
        c.setAddress(cd.getAddress());
        c.setPhone(cd.getPhone());
        c.setEmail(cd.getEmail());
        return c;
    }

    public CustomerDTO mapCustomerToCustomerDTO(Customer c) {
        CustomerDTO cd = new CustomerDTO();
        cd.setFirstName(c.getFirstName());
        cd.setLastName(c.getLastName());
        cd.setAddress(c.getAddress());
        cd.setPhone(c.getPhone());
        cd.setEmail(c.getEmail());
        return cd;
    }

    public CustomerDTOLoginPassword mapCustomerToCustomerDTOLoginPassword(Customer c) {
        CustomerDTOLoginPassword cdlp = new CustomerDTOLoginPassword();
        cdlp.setLogin(c.getLogin());
        cdlp.setPassword(c.getPassword());
        return cdlp;
    }
}