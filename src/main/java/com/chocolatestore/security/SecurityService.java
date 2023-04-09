package com.chocolatestore.security;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.reqest.JwtAuthRequest;
import com.chocolatestore.domain.reqest.RegistrationCustomer;
import com.chocolatestore.repository.CustomerRepository;
import com.chocolatestore.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecurityService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String getToken(JwtAuthRequest jwtAuthRequest) {
        Optional<Customer> customer = customerRepository.findCustomerByLogin(jwtAuthRequest.getLogin());
        if (customer.isPresent() && passwordEncoder.matches(jwtAuthRequest.getPassword(), customer.get().getPassword())) {
            return jwtProvider.createJwtToken(jwtAuthRequest.getLogin());
        }
        return "";
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean registration(RegistrationCustomer registrationUser) {
        try {
            Customer customer = new Customer();
            customer.setFirstName(registrationUser.getFirstName());
            customer.setLastName(registrationUser.getLastName());
            customer.setAddress(registrationUser.getAddress());
            customer.setPhone(registrationUser.getPhone());
            customer.setEmail(registrationUser.getEmail());
            customer.setLogin(registrationUser.getLogin());
            customer.setPassword(passwordEncoder.encode(registrationUser.getPassword()));

            Customer savedCustomer = customerRepository.save(customer);
            customerRepository.addCustomerRole(savedCustomer.getId());

            if (savedCustomer != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
