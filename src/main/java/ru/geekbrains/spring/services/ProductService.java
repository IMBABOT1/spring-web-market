package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(Integer min, Integer max, Integer page){
        List<Product> products = productRepository.findAllByPriceBetween(min, max);
        List<Product> result = new ArrayList<>();
        int position = 0;

        int pageCount = (products.size() / 10) + 1;

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
        } else if (page == pageCount) {
            page = page - 1;
            page *= 10;
            position = products.size();
        } else if (page > pageCount){
            page = pageCount;
            page = page - 1;
            page *= 10;
            position = products.size();
        }
        for (int i = page; i < position; i++) {
            result.add(products.get(i));
        }
        return result;
    }

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changePrice(Long productId, Integer delta) {
        Product p = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product price, id: " + productId));
        p.setPrice(p.getPrice() + delta);

    }

    public void save(Product product) {
        productRepository.save(product);
    }
}

