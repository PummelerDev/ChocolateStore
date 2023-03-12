package com.chocolatestore.mappers;

import com.chocolatestore.domain.Kind;
import com.chocolatestore.domain.Product;
import com.chocolatestore.domain.Topping;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setKind(Kind.valueOf(rs.getString("kind")));
        product.setTopping(Topping.valueOf(rs.getString("topping")));
        product.setManufacturerId(rs.getLong("manufacturer_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setWeight(rs.getInt("weight"));
        product.setPrice(rs.getDouble("price"));
        product.setCreated(rs.getTimestamp("created"));
        product.setChanged(rs.getTimestamp("changed"));
        return product;
    }
}
