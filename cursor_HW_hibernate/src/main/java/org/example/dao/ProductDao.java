package org.example.dao;

import org.example.dmo.Product;
import org.example.dmo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductDao {
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    public List<Product> getProductList() {
        Session session = this.sessionFactory.openSession();
        List<Product> lst = session.createQuery("select p from Product p").list();
        session.close();
        return lst;
    }

    public Product getProductById(long id) {
        Product product = null;
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        product = session.get(Product.class, id);
        transaction.commit();
        return product;
    }

    public void deleteProduct(long id) {
        Transaction transaction = null;
        Product product = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void updateProduct(Product product) {
        Transaction transaction= null;
        try(Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
        }catch (Exception e){

        }
    }

}
