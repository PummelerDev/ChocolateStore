package com.chocolatestore.service;

import com.chocolatestore.domain.Storage;
import com.chocolatestore.exceptions.StorageNotFoundException;
import com.chocolatestore.mappers.StorageMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Service
public class StorageService {

    JdbcTemplate jdbcTemplate;

    public StorageService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Storage> getAllStorages() {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            storages = (ArrayList<Storage>) jdbcTemplate.query(
                    "select * from storages",
                    new StorageMapper()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return storages;
        }
    }

    public Storage getStorageById(long id) {
        Storage storage = new Storage();
        try {
            storage = jdbcTemplate.queryForObject(
                    "select * from storages where id =?",
                    new StorageMapper(),
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return storage;
        }
    }

    public int createStorage(String name) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "insert into storages(id, name, created, changed) values (default, ?, DEFAULT, default)",
                    name
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int updateStorage(Storage storage) {
        int result = 0;
        try {
            Storage theSameStorageFromDB = getStorageById(storage.getId());
            result = jdbcTemplate.update(
                    "update storages set name = ?, changed = default where id=?",
                    StringUtils.isBlank(storage.getName()) ? theSameStorageFromDB.getName() : storage.getName(),
                    storage.getId()
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int deleteStorageById(long id) {
        int result = 0;
        try {
            result = jdbcTemplate.update(
                    "delete from storages where id=?",
                    id
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
