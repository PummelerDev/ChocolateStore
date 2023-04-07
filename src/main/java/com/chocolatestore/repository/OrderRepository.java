package com.chocolatestore.repository;

import com.chocolatestore.domain.DTO.OrderDTORequestAddOrUpdate;
import com.chocolatestore.domain.DTO.OrderDTORequestCreate;
import com.chocolatestore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true, value = "insert into orders values(default, :#{#orderNumber}, :#{#order.productId}, :#{#order.customerId}, :#{#order.quantity}, default, default, default, default) returning *")
    Order saveCustom(@Param("order") OrderDTORequestCreate order, @Param("orderNumber") long orderNumber);

    @Query(nativeQuery = true, value = "insert into orders values(default, :#{#orderNumber}, :#{#order.productId}, (select distinct customer_id from orders where order_number =:#{#orderNumber}), :#{#order.quantity}, default, default, default, default) returning *")
    Order addByOrderNumber(@Param("order") OrderDTORequestAddOrUpdate order, @Param("orderNumber") long orderNumber);

    @Query(nativeQuery = true, value = "update orders set product_id =:#{#order.productId}, quantity =:#{#order.quantity} where id =:#{#id} returning *")
    Order saveAndFlushCustom(@Param("id") long id, @Param("order") OrderDTORequestAddOrUpdate odr);

    @Query(nativeQuery = true, value = "update orders set cancelled = true, changed = default where order_number=:orderNumber and id=:id returning cancelled")
    boolean cancelOrderByNumberAndId(long orderNumber, long id);

    @Query(nativeQuery = true, value = "update orders set cancelled = true, changed = default where order_number=:orderNumber returning cancelled")
    boolean cancelAllOrdersByNumber(long orderNumber);

    @Query(nativeQuery = true, value = "update orders set collected = true, changed = default where order_number=:orderNumber returning collected")
    boolean collectOrderByNumber(long orderNumber);

    // TODO: 07.04.2023 purchase_amount from customers!
    @Query(nativeQuery = true, value = "update orders set finished = true, changed = default where order_number=:orderNumber returning finished")
    boolean finishOrderByNumber(long orderNumber);

    @Query(nativeQuery = true, value = "select * from orders where order_number =:orderNumber and cancelled=false")
    List<Order> findAllByOrderNumber(long orderNumber);
}
