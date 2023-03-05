package com.chocolatestore.controller;

import com.chocolatestore.domain.Product;
import com.chocolatestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProduct(ModelMap modelMap) {
        List<Product> products = productService.getAllProducts();
        modelMap.addAttribute("products", products);
        return "allProducts";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable long id, ModelMap modelMap) {
        Product product = productService.getProductById(id);
        modelMap.addAttribute("product", product);
        return "product";
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        int result = productService.createProduct(product);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @PutMapping
    @ResponseBody
    public String updateProductById(@ModelAttribute Product product) {
        int result = productService.updateProductById(product);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteProductById(@PathVariable long id) {
        int result = productService.deleteProductById(id);
        return result > 0 ? "successful" : "unsuccessful";
    }
}
