package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.DTO.ProductDTORequest;
import com.chocolatestore.exceptions.ProductNotFoundException;
import com.chocolatestore.utils.Kind;
import com.chocolatestore.domain.Product;
import com.chocolatestore.utils.Topping;
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
        ArrayList<Product> products = (ArrayList<Product>) productRepository
                .findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products not found!");
        }
        return (ArrayList<ProductDTOResponse>) products
                .stream()
                .map(productMapper::mapProductToProductDTOResponse)
                .collect(Collectors.toList());
    }

    public ArrayList<Product> getAllProductForAdmin() {
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products not found!");
        }
        return products;
    }

    public ProductDTOResponse getProductById(long id) {
        return productMapper.mapProductToProductDTOResponse(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + "not found!")));
    }

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