package com.chocolatestore.repository;

import com.chocolatestore.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true,value = "insert into customers values(default, :#{#customer.firstName}, :#{#customer.lastName}, :#{#customer.address}, :#{#customer.phone}, :#{#customer.email}, :#{#customer.purchaseAmount}, :#{#customer.login}, :#{#customer.password}, default, default, :#{#customer.deleted}) returning *")
    Customer save(@Param("customer") Customer customer);
}
