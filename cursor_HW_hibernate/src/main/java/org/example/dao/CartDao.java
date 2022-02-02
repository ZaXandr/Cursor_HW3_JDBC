package org.example.dao;

import org.example.dmo.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class CartDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Cart cart) {
        try (Session session = this.sessionFactory.openSession()
        ) {
            Transaction tx = session.beginTransaction();
            session.save(cart);
            tx.commit();
        }catch (Exception e){

        }
    }

    public List<Cart> getCartList(){
        List<Cart> carts = null;
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            TypedQuery<Cart> q = session.createQuery("SELECT c FROM Cart",Cart.class);
            carts = q.getResultList();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return carts;
    }

    public Cart getCartById(long id){
        Cart cart = null;
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cart = session.get(Cart.class,id);
            transaction.commit();
        }catch (Exception e){

        }
        return cart;
    }

    public void updateCategory(Cart cart) {
        Transaction transaction= null;
        try(Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(cart);
            transaction.commit();
        }catch (Exception e){

        }
    }

    public void removeCart(long id){
        Transaction transaction= null;
        try(Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(session.get(Cart.class,id));
            transaction.commit();
        }catch (Exception e){

        }
    }

}
