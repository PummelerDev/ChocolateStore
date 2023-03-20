package com.chocolatestore.repository;

import com.chocolatestore.domain.Order;
import com.chocolatestore.domain.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class OrderRepository {

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Order");
            orders = (ArrayList<Order>) query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderById(long id) {
        Order order = new Order();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Order> optionalOrder = Optional.of(session.get(Order.class, id));
            order = optionalOrder.orElse(new Order());
            session.getTransaction().commit();
            if (optionalOrder == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | HibernateException e) {
            e.printStackTrace();
            return new Order();
        }
        return order;

    }

    public void createOrder(Order order) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderById(Order order) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
