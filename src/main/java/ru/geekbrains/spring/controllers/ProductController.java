package ru.geekbrains.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.converters.ProductConverter;
import ru.geekbrains.spring.dto.ProductDto;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.exceptions.AppError;
import ru.geekbrains.spring.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    public ProductConverter productConverter;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }


        return productService.find(minPrice, maxPrice, titlePart, page).map(product -> productConverter.entityToDto(product));
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id not found id: " + id));
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        productService.save(productConverter.dtoToEntity(productDto));
    }

    @PutMapping
    public void editProduct(@RequestBody ProductDto productDto) {
        productService.save(productConverter.dtoToEntity(productDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
