package com.chocolatestore.mappers;

import com.chocolatestore.domain.Manufacturer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ManufacturerMapper implements RowMapper<Manufacturer> {
    @Override
    public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(rs.getLong("id"));
        manufacturer.setName(rs.getString("name"));
        manufacturer.setCreated(rs.getTimestamp("created"));
        manufacturer.setChanged(rs.getTimestamp("changed"));
        return manufacturer;
    }
}
