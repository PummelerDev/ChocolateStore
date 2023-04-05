package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.OrderDTOResponse;
import com.chocolatestore.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
