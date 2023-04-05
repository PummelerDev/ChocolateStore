package com.chocolatestore.service;

import com.chocolatestore.domain.Customer;
import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.DTO.ProductDTORequest;
import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Product;
import com.chocolatestore.domain.Topping;
import com.chocolatestore.mappers.ProductMapper;
import com.chocolatestore.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ArrayList<ProductDTOResponse> getAllProducts() {
        return (ArrayList<ProductDTOResponse>) productRepository
                .findAll()
                .stream()
                .map(productMapper::mapProductToProductDTOResponse)
                .collect(Collectors.toList());
    }

    // TODO: 31.03.2023 productRepository returns optional. need to rewrite it.
    public ProductDTOResponse getProductById(long id) {
        return productMapper.mapProductToProductDTOResponse(productRepository.findById(id).get());
    }

    // TODO: 04.04.2023 it's working?
    public Product createProduct(ProductDTORequest pdr) {
        return productRepository.save(pdr);
    }

    public Product updateProductById(long id, ProductDTORequest pdr) {
        Product fromDB = productRepository.findById(id).get();
        ProductDTORequest intoDB = new ProductDTORequest();
        intoDB.setKind(Kind.valueOf(pdr.getKind() == fromDB.getKind() ? fromDB.getKind().toString() : pdr.getKind().toString()));
        intoDB.setTopping(Topping.valueOf(pdr.getTopping() == fromDB.getTopping() ? fromDB.getTopping().toString() : pdr.getTopping().toString()));
        intoDB.setManufacturerId(pdr.getManufacturerId() >= 0 ? pdr.getManufacturerId() : fromDB.getManufacturer().getId());
        intoDB.setName(StringUtils.isBlank(pdr.getName()) ? fromDB.getName() : pdr.getName());
        intoDB.setDescription(StringUtils.isBlank(pdr.getDescription()) ? fromDB.getDescription() : pdr.getDescription());
        intoDB.setWeight((pdr.getWeight() != fromDB.getWeight() && pdr.getWeight() >= 0) ? pdr.getWeight() : fromDB.getWeight());
        intoDB.setPrice((pdr.getPrice() != fromDB.getPrice() && pdr.getPrice() >= 0) ? pdr.getPrice() : fromDB.getPrice());
        return productRepository.saveAndFlushCustom(id, intoDB);
    }

    public boolean removeProductById(long id) {
        productRepository.deleteById(id);
        return !productRepository.existsById(id);
    }
}
