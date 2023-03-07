package ru.geekbrains.spring.repositories;

import ru.geekbrains.spring.entities.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();
    Product findProductById(Long id);
    void deleteProductById(Long id);
    void changePrice(Long productId, int delta);


}
