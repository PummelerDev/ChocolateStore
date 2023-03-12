package com.chocolatestore.service;

import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Product;
import com.chocolatestore.domain.Topping;
import com.chocolatestore.exceptions.ProductNotFoundException;
import com.chocolatestore.mappers.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Service
public class ProductService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            products = (ArrayList<Product>) jdbcTemplate.query(
                    "select * from products",
                    new ProductMapper()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(long id) {
        Product product = new Product();
        try {
            product = (Product) jdbcTemplate.query(
                    "select * from products where id =?",
                    new ProductMapper(),
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (product.getId() <= 0) {
            throw new ProductNotFoundException("there is no product with id " + id);
        }
        return product;
    }

    public int createProduct(Product product) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "insert into products(id, kind, topping, manufacturer_id, name, description, weight, price, created, changed) values(default, ?, ?, ?, ?, ?, ?, ?, default, default)",
                    product.getKind().toString(),
                    product.getTopping().toString(),
                    product.getManufacturerId(),
                    product.getName(),
                    product.getDescription(),
                    product.getWeight(),
                    product.getPrice()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateProductById(Product product) {
        int result = 0;
        try {
            Product theSameProductFromDB = getProductById(product.getId());
            result = jdbcTemplate.update(
                    "update products set kind=?, topping=?, manufacturer=?, name=?, description=?, weight=?, price=?, changed=default where id=?",
                    StringUtils.isBlank(product.getKind().toString()) ? theSameProductFromDB.getKind().toString() : product.getKind().toString(),
                    StringUtils.isBlank(product.getTopping().toString()) ? theSameProductFromDB.getTopping().toString() : product.getTopping().toString(),
                    product.getManufacturerId() == 0 ? theSameProductFromDB.getManufacturerId() : product.getManufacturerId(),
                    StringUtils.isBlank(product.getName()) ? theSameProductFromDB.getName() : product.getName(),
                    StringUtils.isBlank(product.getDescription()) ? theSameProductFromDB.getDescription() : product.getDescription(),
                    product.getWeight() == 0 ? theSameProductFromDB.getWeight() : product.getWeight(),
                    product.getPrice() == 0 ? theSameProductFromDB.getPrice() : product.getPrice(),
                    product.getId()
            );
        } catch (DataAccessException | ProductNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteProductById(long id) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "delete from products where id=?",
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
