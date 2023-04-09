package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.ProductDTOResponse;
import com.chocolatestore.domain.DTO.ProductDTORequest;
import com.chocolatestore.domain.Product;
import com.chocolatestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<ProductDTOResponse>> getAllProduct() {
        List<ProductDTOResponse> pdr = productService.getAllProducts();
        return new ResponseEntity<>(pdr, HttpStatus.OK);
    }

    @GetMapping("/all/admin")
    public ResponseEntity<List<Product>> getAllProductForAdmin() {
        List<Product> p = productService.getAllProductForAdmin();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable long id) {
        ProductDTOResponse pdr = productService.getProductById(id);
        return new ResponseEntity<>(pdr, pdr != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createProduct(@RequestBody @Valid ProductDTORequest pdr, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Product p = productService.createProduct(pdr);
        return new ResponseEntity<>(p != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateProductById(@PathVariable long id, @RequestBody @Valid ProductDTORequest pdr, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Product p = productService.updateProductById(id, pdr);
        return new ResponseEntity<>(p != null ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/get/{id}/remove")
    public ResponseEntity<HttpStatus> removeProductById(@PathVariable long id) {
        boolean result = productService.removeProductById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
