package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.repositories.HibernateProductRepository;
import ru.geekbrains.spring.repositories.InMemProductRepository;
import ru.geekbrains.spring.repositories.ProductDao;

import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductRepository(HibernateProductRepository hibernateProductRepository) {
        this.productDao = hibernateProductRepository;
    }

    public List<Product> findAll(){
        return productDao.findAll();
    }

    public Product findProductById(Long id){
        return productDao.findProductById(id);
    }

    public void deleteProductById(Long id) {
        productDao.deleteProductById(id);
    }

    public void changePrice(Long productId, Integer delta) {
        productDao.changePrice(productId, delta);
    }
}

