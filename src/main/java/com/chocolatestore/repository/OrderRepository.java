package com.chocolatestore.repository;

import com.chocolatestore.domain.DTO.OrderDTORequest;
import com.chocolatestore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true, value = "insert into orders values(default, :#{#orderNumber}, :#{#order.productId}, :#{#order.customerId}, :#{#order.quantity}, default, default, default, default) returning *")
    Order save(@Param("order") OrderDTORequest order, @Param("orderNumber") long orderNumber);

    @Query(nativeQuery = true, value = "update orders set product_id =:#{#order.productId}, quantity =:#{#order.quantity} where id =:#{#id} returning *")
    Order saveAndFlushCustom(@Param("id") long id , @Param("order") OrderDTORequest odr);

}
