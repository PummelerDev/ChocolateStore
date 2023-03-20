package com.chocolatestore.repository;

import com.chocolatestore.domain.Product;
import com.chocolatestore.domain.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class StorageRepository {

    public ArrayList<Storage> getAllStorages() {
        ArrayList<Storage> storages = new ArrayList<>();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Storage ");
            storages = (ArrayList<Storage>) query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return storages;
    }

    public Storage getStorageById(long id) {
        Storage storage = new Storage();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Storage> optionalStorage = Optional.of(session.get(Storage.class, id));
            storage = optionalStorage.orElse(new Storage());
            session.getTransaction().commit();
            if (optionalStorage == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | HibernateException e) {
            e.printStackTrace();
            return new Storage();
        }
        return storage;
    }

    public void createStorage(Storage storage) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(storage);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void updateStorage(Storage storage) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(storage);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteStorageById(Storage storage) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(storage);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
