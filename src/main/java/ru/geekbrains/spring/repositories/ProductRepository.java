package ru.geekbrains.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
