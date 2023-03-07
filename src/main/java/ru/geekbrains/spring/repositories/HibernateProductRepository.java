package ru.geekbrains.spring.repositories;

import jakarta.annotation.PostConstruct;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.entities.Product;
import ru.geekbrains.spring.utils.SessionFactoryUtils;

import java.util.List;
@Repository
public class HibernateProductRepository implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public void setSessionFactoryUtils(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @PostConstruct
    private void init(){
        sessionFactoryUtils.init();
    }


    @Override
    public List<Product> findAll() {
        Session session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("SELECT p FROM Product p").getResultList();
        session.getTransaction().commit();
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        Session session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        Product p = session.get(Product.class, id);
        session.getTransaction().commit();
        return p;
    }

    @Override
    public void deleteProductById(Long id) {
        Session session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        Product p = session.get(Product.class, id);
        session.delete(p);
        session.getTransaction().commit();
    }

    @Override
    public void changePrice(Long productId, int delta) {
        Session session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        Product p = session.get(Product.class, productId);
        p.setPrice(p.getPrice() + delta);
        session.getTransaction().commit();
    }
}
