package com.chocolatestore.mappers;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.DTO.ProductDTO;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class HibernateDTOMapper {
    public static ManufacturerDTO getManufacturerDTO(Manufacturer manufacturer) {
        List<Product> productList = new ArrayList<>();
        for (Product product : manufacturer.getProducts()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setKind(product.getKind());
            productDTO.setTopping(product.getTopping());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setWeight(product.getWeight());
            productDTO.setCreated(product.getCreated());
            productDTO.setChanged(product.getChanged());
        }
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setId(manufacturer.getId());
        manufacturerDTO.setName(manufacturer.getName());
        manufacturerDTO.setCreated(manufacturer.getCreated());
        manufacturerDTO.setChanged(manufacturer.getChanged());
        manufacturerDTO.setProducts(productList);
        return manufacturerDTO;
    }

    public static ProductDTO getProductDTO(Product product){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(product.getManufacturer().getId());
        manufacturer.setName(product.getManufacturer().getName());
        manufacturer.setCreated(product.getManufacturer().getCreated());
        manufacturer.setChanged(product.getManufacturer().getChanged());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setKind(product.getKind());
        productDTO.setTopping(product.getTopping());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setCreated(product.getCreated());
        productDTO.setChanged(product.getChanged());
        productDTO.setManufacturer(manufacturer);
        return productDTO;
    }
}
