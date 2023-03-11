package ru.geekbrains.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Product> findAll(@RequestParam(defaultValue = "0") Integer min,
                                 @RequestParam(defaultValue = "2147483647") Integer max,
                                 @RequestParam(defaultValue = "0") Integer page) {
        List<Product> products = productService.findAll(min, max);
        List<Product> result = new ArrayList<>();
        int position = 0;

        int pageCount = (products.size() / 10) + 1;
        System.out.println(pageCount);

        if (page == null) {
            page = 0;
            position = products.size();
        } else if (page == 0) {
            page = 0;
            position = products.size();
        } else if (page > 0 && page < pageCount) {
            int index = page;
            page = page - 1;
            page *= 10;
            position = 10 * index;
        }else if (page == pageCount){
            page = page - 1;
            page *= 10;
            position = products.size();
        }

        for (int i = page; i < position; i++) {
            result.add(products.get(i));
        }
        return result;
    }

    @GetMapping("/products/product_add")
    public void addProduct(@RequestParam String title, @RequestParam Integer price) {
        Product product = new Product(null, title, price);
        productService.save(product);
    }


    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id not found id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/products/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changePrice(productId, delta);
    }
}
