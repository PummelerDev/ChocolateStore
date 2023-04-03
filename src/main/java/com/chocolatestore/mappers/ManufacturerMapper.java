package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManufacturerMapper {
    private final ProductMapper productMapper;

    @Autowired
    public ManufacturerMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

//    public Manufacturer mapManufacturerDTOToManufacturer(ManufacturerDTO md) {
//        Manufacturer m = new Manufacturer();
//        m.setName(md.getName());
//        m.setProducts(md.getProducts());
//        return m;
//    }

    public ManufacturerDTO mapManufacturerToManufacturerDTO(Manufacturer m) {
        List<ProductDTOResponse> productList = m.getProducts()
                .stream()
                .map(productMapper::mapProductToProductDTOResponse)
                .collect(Collectors.toList());
        ManufacturerDTO md = new ManufacturerDTO();
        md.setName(m.getName());
        md.setProducts(productList);
        return md;
    }

}
