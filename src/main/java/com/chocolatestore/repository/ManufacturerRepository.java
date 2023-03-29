package com.chocolatestore.repository;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.mappers.HibernateDTOMapper;
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

    public ArrayList<ManufacturerDTO> getAllManufacturers() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        ArrayList<ManufacturerDTO> manufacturersDTO = new ArrayList<>();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Manufacturer");
            manufacturers = (ArrayList<Manufacturer>) query.getResultList();
            for (Manufacturer manufacturer : manufacturers) {
                manufacturersDTO.add(HibernateDTOMapper.getManufacturerDTO(manufacturer));
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return manufacturersDTO;
    }

    public ManufacturerDTO getManufacturerById(long id) {
        Manufacturer manufacturer = new Manufacturer();
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Manufacturer> optionalManufacturer = Optional.of(session.get(Manufacturer.class, id));
            manufacturer = optionalManufacturer.orElse(new Manufacturer());
            manufacturerDTO = HibernateDTOMapper.getManufacturerDTO(manufacturer);
            session.getTransaction().commit();
            if (optionalManufacturer == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | HibernateException e) {
            e.printStackTrace();
            return new ManufacturerDTO();
        }
        return manufacturerDTO;

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
