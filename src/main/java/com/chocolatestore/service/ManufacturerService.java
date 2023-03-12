package com.chocolatestore.service;

import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.exceptions.ManufacturerNotFoundException;
import com.chocolatestore.mappers.ManufacturerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Service
public class ManufacturerService {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ManufacturerService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Manufacturer> getAllManufacturers() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        try {
            manufacturers = (ArrayList<Manufacturer>) jdbcTemplate.query(
                    "select * from manufacturers",
                    new ManufacturerMapper()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public Manufacturer getManufacturerById(long id) {
        Manufacturer manufacturer = new Manufacturer();
        try {
            manufacturer = (Manufacturer) jdbcTemplate.query(
                    "select * from manufacturers where id =?",
                    new ManufacturerMapper(),
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (manufacturer.getId() <= 0) {
            throw new ManufacturerNotFoundException("there is no manufacturer with id " + id);
        }
        return manufacturer;
    }

    public int createManufacturer(String name) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "insert into manufacturers(id, name, created, changed) values (default, ?, default, default)",
                    name
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateManufacturer(Manufacturer manufacturer) {
        int result = 0;
        try {
            Manufacturer theSameManufacturerFromDB = getManufacturerById(manufacturer.getId());
            result = jdbcTemplate.update(
                    "update manufacturers set name = ?, changed = default where id=?",
                    StringUtils.isBlank(manufacturer.getName()) ? theSameManufacturerFromDB.getName() : manufacturer.getName(),
                    manufacturer.getId()
            );
        } catch (DataAccessException | ManufacturerNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteManufacturerById(long id) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "delete from manufacturers where id=?",
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}