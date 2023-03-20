package com.chocolatestore.service;

import com.chocolatestore.domain.Storage;
import com.chocolatestore.mappers.StorageMapper;
import com.chocolatestore.repository.StorageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;

@Service
public class StorageService {

    StorageRepository storageRepository;

    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public ArrayList<Storage> getAllStorages() {
        return storageRepository.getAllStorages();
    }

    public Storage getStorageById(long id) {
        return storageRepository.getStorageById(id);
    }

    public void createStorage(String name) {
        Storage storage = new Storage();
        storage.setName(name);
        storageRepository.createStorage(storage);
    }

    public void updateStorage(Storage storage) {
        storageRepository.updateStorage(storage);
    }

    public void deleteStorageById(long id) {
        Storage storage = new Storage();
        storage.setId(id);
        storageRepository.deleteStorageById(storage);
    }
}
