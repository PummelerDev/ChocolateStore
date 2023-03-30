package com.chocolatestore.service;

import com.chocolatestore.domain.Storage;
import com.chocolatestore.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StorageService {

    StorageRepository storageRepository;

    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public ArrayList<Storage> getAllStorages() {
        return (ArrayList<Storage>) storageRepository.findAll();
    }

    public Storage getStorageById(long id) {
        return storageRepository.findById(id).get();
    }

    public Storage createStorage(String name) {
        Storage storage = new Storage();
        storage.setName(name);
        return storageRepository.save(storage);
    }

    public Storage updateStorage(Storage storage) {
        return storageRepository.saveAndFlush(storage);
    }

    public void deleteStorageById(long id) {
        storageRepository.deleteById(id);
    }

    public void deleteStorage(Storage storage) {
        storageRepository.delete(storage);
    }
}
