package com.chocolatestore.repository;

import com.chocolatestore.domain.Manufacturer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    public ArrayList<Manufacturer> getAllManufacturers() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacturer");
            manufacturers = (ArrayList<Manufacturer>) query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public Manufacturer getManufacturerById(long id) {
        Manufacturer manufacturer = new Manufacturer();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Manufacturer> optionalManufacturer = Optional.of(session.get(Manufacturer.class, id));
            manufacturer = optionalManufacturer.orElse(new Manufacturer());
//            manufacturer = session.get(Manufacturer.class, id);
            session.getTransaction().commit();
            if (optionalManufacturer == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | HibernateException e) {
            e.printStackTrace();
            return new Manufacturer();
        }
        return manufacturer;

    }

    public void createManufacturer(Manufacturer manufacturer) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(manufacturer);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteManufacturerById(Manufacturer manufacturer) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
