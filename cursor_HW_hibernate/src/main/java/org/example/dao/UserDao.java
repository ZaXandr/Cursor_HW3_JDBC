package org.example.dao;

import org.example.dmo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User p) {
        try(Session session = this.sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();
            session.save(p);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public List<User> getUsersList() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<User> q = session.createQuery("SELECT u FROM User u", User.class);
            users =  q.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
               // transaction.rollback();
            }
        }
        return users;
    }

    public User getById(long id) {
        User user = null;
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class,id);
            transaction.commit();
        }catch (Exception e){

        }
        return user;
    }

    public void updateUser(User user) {
        Transaction transaction= null;
        try(Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }catch (Exception e){

        }

    }

    public void deleteUser(long id) {
        Transaction transaction = null;
        User user = null;
        try(Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            user = session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e){

        }
    }
}
