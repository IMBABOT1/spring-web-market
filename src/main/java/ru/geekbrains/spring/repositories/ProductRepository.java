package ru.geekbrains.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(Integer min, Integer max);
}
