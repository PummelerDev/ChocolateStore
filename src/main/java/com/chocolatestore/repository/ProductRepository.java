package com.chocolatestore.repository;

import com.chocolatestore.domain.DTO.ProductDTO;
import com.chocolatestore.domain.Product;
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
public class ProductRepository {

    public ArrayList<ProductDTO> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<ProductDTO> productsDTO = new ArrayList<>();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Product ");
            products = (ArrayList<Product>) query.getResultList();
            for (Product product :
                    products) {
                productsDTO.add(HibernateDTOMapper.getProductDTO(product));
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return productsDTO;
    }

    public ProductDTO getProductById(long id) {
        Product product = new Product();
        ProductDTO productDTO = new ProductDTO();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Product> optionalProduct = Optional.of(session.get(Product.class, id));
            product = optionalProduct.orElse(new Product());
            productDTO = HibernateDTOMapper.getProductDTO(product);
            session.getTransaction().commit();
            if (optionalProduct == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException | HibernateException e) {
            e.printStackTrace();
            return new ProductDTO();
        }
        return productDTO;

    }

    public void createProduct(Product product) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(Product product) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
