package ru.geekbrains.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.exceptions.AppError;
import ru.geekbrains.spring.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public Page<Product> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, titlePart, page);
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id not found id: " + id));
    }

    @PostMapping("/products/product_add")
    public void addProduct(@RequestBody Product product) {
        product.setId(null);
        productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }
}
