package com.chocolatestore.repository;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.CustomerDTOLoginPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "insert into customers values(default, :#{#customer.firstName}, :#{#customer.lastName}, :#{#customer.address}, :#{#customer.phone}, :#{#customer.email}, :#{#customer.purchaseAmount}, :#{#customer.login}, :#{#customer.password}, default, default, :#{#customer.deleted}) returning *")
    Customer save(@Param("customer") Customer customer);

    @Query(nativeQuery = true, value = "update customers set is_deleted = true, changed = default where id = :id returning is_deleted")
    boolean deleteByIdCustom(long id);

    @Query(nativeQuery = true, value = "update customers set is_deleted = false, changed = default where id = :id returning true")
    boolean restoreByIdCustom(long id);

    @Query(nativeQuery = true, value = "update customers set login  =:#{#cdlp.login}, password =:#{#cdlp.password}, changed =default where id =:id returning true")
    boolean updateLoginPassword(long id, @Param("cdlp") CustomerDTOLoginPassword cdlp);

    Optional<Customer> findCustomerByLogin(String login);

    @Query(nativeQuery = true, value = "select role from roles where customer_id=:id")
    String getRole(long id);

    @Modifying
    @Query(nativeQuery = true, value = "insert into roles values (default, :customerId, default, default)")
    void addCustomerRole(long customerId);
}