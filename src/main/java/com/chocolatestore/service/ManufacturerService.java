package com.chocolatestore.service;

import com.chocolatestore.domain.Manufacturer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class ManufacturerService {

    public ArrayList<Manufacturer> getAllManufacturers() {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from manufacturers"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturerMapping(manufacturer, resultSet);
                manufacturers.add(manufacturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public Manufacturer getManufacturerById(long id) {
        Manufacturer manufacturer = new Manufacturer();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from manufacturers where id =?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            manufacturerMapping(manufacturer, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

    public int createManufacturer(String name) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into manufacturers(id, name, created, changed) values (default, ?, default, default)"
            );
            preparedStatement.setString(1, name);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 26.02.2023 can i use manufacturer like a param? or should i use fields from manufacturer?
    public int updateManufacturer(Manufacturer manufacturer) {
        int result = 0;
        try {
            Manufacturer theSameManufacturerFromDB = getManufacturerById(manufacturer.getId());
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update manufacturers set name = ?, changed = default where id=?"
            );
            preparedStatement.setString(1, StringUtils.isBlank(manufacturer.getName()) ? theSameManufacturerFromDB.getName() : manufacturer.getName());
            preparedStatement.setLong(2, manufacturer.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteManufacturerById(long id) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from manufacturers where id=?"
            );
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void manufacturerMapping(Manufacturer manufacturer, ResultSet resultSet) throws SQLException {
        manufacturer.setId(resultSet.getLong("id"));
        manufacturer.setName(resultSet.getString("name"));
        manufacturer.setCreated(resultSet.getTimestamp("created"));
        manufacturer.setChanged(resultSet.getTimestamp("changed"));
    }
}