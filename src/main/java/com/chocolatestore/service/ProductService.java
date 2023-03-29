package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.ProductDTO;
import com.chocolatestore.domain.Product;
import com.chocolatestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<ProductDTO> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public ProductDTO getProductById(long id) {
        return productRepository.getProductById(id);
    }

    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void updateProductById(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProductById(long id) {
        Product product = new Product();
        product.setId(id);
        productRepository.deleteProductById(product);
    }
}
