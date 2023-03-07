package ru.geekbrains.spring.repositories;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.entities.Product;

import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1l, "first", 10),
                new Product(2l, "second", 20),
                new Product(3l, "third", 30),
                new Product(4l, "four", 40),
                new Product(5l, "fifth", 50)
        ));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findProductById(Long id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("Product not found " + id);
    }

    public void deleteProductById(Long id) {
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                return;
            }
        }
    }
}
