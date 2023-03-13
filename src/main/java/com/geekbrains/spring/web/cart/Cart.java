package com.geekbrains.spring.web.cart;

import com.geekbrains.spring.web.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> cart;

    @PostConstruct
    private void init(){
        cart = new ArrayList<>();
    }


    public List<Product> getCart() {
        return cart;
    }


    public void addToCart(Product product){
        cart.add(product);
    }

    public void deleteProduct(Product product){
        cart.remove(product);
    }

}
