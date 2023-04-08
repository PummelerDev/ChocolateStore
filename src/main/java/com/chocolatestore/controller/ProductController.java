package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.DTO.ProductDTORequest;
import com.chocolatestore.domain.Product;
import com.chocolatestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<List<ProductDTOResponse>> getAllProduct() {
        List<ProductDTOResponse> pdr = productService.getAllProducts();
        return new ResponseEntity<>(pdr, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable long id) {
        ProductDTOResponse pdr = productService.getProductById(id);
        return new ResponseEntity<>(pdr, pdr != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTORequest pdr) {
        productService.createProduct(pdr);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProductById(@PathVariable long id, @RequestBody ProductDTORequest pdr) {
        Product p = productService.updateProductById(id, pdr);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> removeProductById(@PathVariable long id) {
        boolean result = productService.removeProductById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
