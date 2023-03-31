package com.chocolatestore.repository;

import com.chocolatestore.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByLogin(String login);

    @Query(nativeQuery = true, value = "select role from roles where customer_id=:id")
    String getRole(long id);

    @Modifying
    @Query(nativeQuery = true, value = "insert into roles values (default, :customerId, default, default)")
    void addCustomerRole(long customerId);
}
