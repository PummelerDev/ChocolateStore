package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.DTO.OrderDTOResponseByNumber;
import com.chocolatestore.domain.DTO.ProductDTOResponseByNumber;
import com.chocolatestore.domain.Order;
import com.chocolatestore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class OrderMapper {

    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public OrderMapper(ProductMapper productMapper, CustomerMapper customerMapper) {
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
    }

    public OrderDTOResponse mapOrderToOrderDTOResponse(Order o) {
        OrderDTOResponse odr = new OrderDTOResponse();
        odr.setOrderNumber(o.getOrderNumber());
        odr.setProduct(productMapper.mapProductToProductDTOResponse(o.getProduct()));
        odr.setCustomer(customerMapper.mapCustomerToCustomerDTO(o.getCustomer()));
        odr.setQuantity(o.getQuantity());
        odr.setCreated(o.getCreated());
        odr.setChanged(o.getChanged());
        odr.setCancelled(o.isCancelled());
        odr.setCollected(o.isCollected());
        odr.setFinished(o.isFinished());
        return odr;
    }

    public OrderDTOResponseByNumber mapOrderToOrderDTOResponseByNumber(List<Order> ol) {
        OrderDTOResponseByNumber odrn = new OrderDTOResponseByNumber();
        if (ol.isEmpty()) {
            return odrn;
        }
        odrn.setOrderNumber(ol.get(0).getOrderNumber());
        odrn.setCustomer(customerMapper.mapCustomerToCustomerDTO(ol.get(0).getCustomer()));
        odrn.setProducts(ol
                .stream()
                .map(order -> productMapper.mapProductToProductDTOResponseByNumber(order.getProduct(), order))
                .collect(Collectors.toList())
        );
        odrn.setTotalPrice(odrn
                .getProducts()
                .stream()
                .map(productDTOResponseByNumber -> productDTOResponseByNumber.getPrice() * productDTOResponseByNumber.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum()
        );
        odrn.setCollected(ol.get(0).isCollected());
        odrn.setFinished(ol.get(0).isFinished());
        return odrn;
    }
}