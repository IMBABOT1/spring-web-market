package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.findById(id).get();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

//    public void changePrice(Long productId, Integer delta) {
//        productDao.changePrice(productId, delta);
//    }
}

