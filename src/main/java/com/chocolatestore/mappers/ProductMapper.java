package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.DTO.ProductDTOResponseByNumber;
import com.chocolatestore.domain.Order;
import com.chocolatestore.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTOResponse mapProductToProductDTOResponse(Product p) {
        ProductDTOResponse pdr = new ProductDTOResponse();
        pdr.setKind(p.getKind());
        pdr.setTopping(p.getTopping());
        pdr.setManufacturerName(p.getManufacturer().getName());
        pdr.setName(p.getName());
        pdr.setDescription(p.getDescription());
        pdr.setWeight(p.getWeight());
        pdr.setPrice(p.getPrice());
        return pdr;
    }

    public ProductDTOResponseByNumber mapProductFromOrderToProductDTOResponseByNumber(Order o) {
        ProductDTOResponseByNumber pdrn = new ProductDTOResponseByNumber();
        pdrn.setKey(o.getId());
        pdrn.setKind(o.getProduct().getKind());
        pdrn.setTopping(o.getProduct().getTopping());
        pdrn.setManufacturerName(o.getProduct().getManufacturer().getName());
        pdrn.setName(o.getProduct().getName());
        pdrn.setDescription(o.getProduct().getDescription());
        pdrn.setWeight(o.getProduct().getWeight());
        pdrn.setPrice(o.getProduct().getPrice());
        pdrn.setQuantity(o.getQuantity());
        return pdrn;
    }
}