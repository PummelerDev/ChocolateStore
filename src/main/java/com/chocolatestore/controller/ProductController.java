package com.chocolatestore.controller;

import com.chocolatestore.domain.Product;
import com.chocolatestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, product.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody Product product) {
        int result = productService.createProduct(product);
        return new ResponseEntity<>(result > 0 ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateProductById(@RequestBody Product product) {
        int result = productService.updateProductById(product);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable long id) {
        int result = productService.deleteProductById(id);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
