package com.chocolatestore.service;

import com.chocolatestore.domain.Storage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class StorageService {


    public ArrayList<Storage> getAllStorages() {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from storages"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Storage storage = new Storage();
                storageMapping(storage, resultSet);
                storages.add(storage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storages;
    }

    public Storage getStorageById(long id) {
        Storage storage = new Storage();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from storages where id =?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            storageMapping(storage, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storage;
    }

    public int createStorage(String name) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into storages(id, name, created, changed) values (default, ?, DEFAULT, default)"
            );
            preparedStatement.setString(1, name);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 26.02.2023 can i use manufacturer like a param? or should i use fields from manufacturer?
    public int updateStorage(Storage storage) {
        int result = 0;
        try {
            Storage theSameStorageFromDB = getStorageById(storage.getId());
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update storages set name = ?, changed = default where id=?"
            );
            preparedStatement.setString(1, StringUtils.isBlank(storage.getName()) ? theSameStorageFromDB.getName() : storage.getName());
            preparedStatement.setLong(2, storage.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteStorageById(long id) {
        int result = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/chocolateStoreDB", "postgres", "root"
            );
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from storages where id=?"
            );
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void storageMapping(Storage storage, ResultSet resultSet) throws SQLException {
        storage.setId(resultSet.getLong("id"));
        storage.setName(resultSet.getString("name"));
        storage.setCreated(resultSet.getTimestamp("created"));
        storage.setChanged(resultSet.getTimestamp("changed"));
    }
}
