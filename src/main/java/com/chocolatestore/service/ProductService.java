package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.ProductDTO;
import com.chocolatestore.domain.Product;
import com.chocolatestore.mappers.HibernateDTOMapper;
import com.chocolatestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product product :
                products) {
            productsDTO.add(HibernateDTOMapper.getProductDTO(product));
        }
        return (ArrayList<ProductDTO>) productsDTO;
    }

    // TODO: 31.03.2023 productRepocitory returns optional. need to rewrite it.
    public ProductDTO getProductById(long id) {
        return HibernateDTOMapper.getProductDTO(productRepository.findById(id).get());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductById(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
