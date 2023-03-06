package com.chocolatestore.service;

import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Product;
import com.chocolatestore.domain.Topping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ProductService {

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from products"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                productMapping(product, resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(long id) {
        Product product = new Product();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from products where id =?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productMapping(product, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // TODO: 26.02.2023 can i use product like a param? or should i use fields from product?
    public int createProduct(Product product) {
        int value = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into products(id, kind, topping, manufacturer_id, name, description, weight, price, created, changed) values(default, ?, ?, ?, ?, ?, ?, ?, default, default)"
            );
            preparedStatement.setString(1, product.getKind().toString());
            preparedStatement.setString(2, product.getTopping().toString());
            preparedStatement.setLong(3, product.getManufacturerId());
            preparedStatement.setString(4, product.getName());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getWeight());
            preparedStatement.setDouble(7, product.getPrice());
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    // TODO: 26.02.2023 can i use product like a param? or should i use fields from product?
    public int updateProductById(Product product) {
        int value = 0;
        try {
            Product theSameProductFromDB = getProductById(product.getId());
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update products set kind=?, topping=?, manufacturer=?, name=?, description=?, weight=?, price=?, changed=default where id=?"
            );
            preparedStatement.setString(1, StringUtils.isBlank(product.getKind().toString()) ? theSameProductFromDB.getKind().toString() : product.getKind().toString());
            preparedStatement.setString(2, StringUtils.isBlank(product.getTopping().toString()) ? theSameProductFromDB.getTopping().toString() : product.getTopping().toString());
            preparedStatement.setLong(3, product.getManufacturerId() == 0 ? theSameProductFromDB.getManufacturerId() : product.getManufacturerId());
            preparedStatement.setString(4, StringUtils.isBlank(product.getName()) ? theSameProductFromDB.getName() : product.getName());
            preparedStatement.setString(5, StringUtils.isBlank(product.getDescription()) ? theSameProductFromDB.getDescription() : product.getDescription());
            preparedStatement.setInt(6, product.getWeight() == 0 ? theSameProductFromDB.getWeight() : product.getWeight());
            preparedStatement.setDouble(7, product.getPrice() == 0 ? theSameProductFromDB.getPrice() : product.getPrice());
            preparedStatement.setLong(8, product.getId());
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int deleteProductById(long id) {
        int value = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from products where id=?"
            );
            preparedStatement.setLong(1, id);
            value = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    private void productMapping(Product product, ResultSet resultSet) throws SQLException {
        product.setId(resultSet.getLong("id"));
        product.setKind(Kind.valueOf(resultSet.getString("kind")));
        product.setTopping(Topping.valueOf(resultSet.getString("topping")));
        product.setManufacturerId(resultSet.getLong("manufacturer_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setWeight(resultSet.getInt("weight"));
        product.setPrice(resultSet.getDouble("price"));
        product.setCreated(resultSet.getTimestamp("created"));
        product.setChanged(resultSet.getTimestamp("changed"));
    }
}
