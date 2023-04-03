package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

//    public Product mapProductDTOResponseToProduct(ProductDTOResponse pd) {
//        Product p = new Product();
//        p.setKind(pd.getKind());
//        p.setTopping(pd.getTopping());
//        p.setManufacturer(pd.getManufacturer());
//        p.setName(pd.getName());
//        p.setDescription(pd.getDescription());
//        p.setWeight(pd.getWeight());
//        p.setPrice(pd.getPrice());
//        return p;
//    }

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
}
