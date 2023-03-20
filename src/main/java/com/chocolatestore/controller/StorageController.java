package com.chocolatestore.controller;

import com.chocolatestore.domain.Storage;
import com.chocolatestore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<Storage>> getAllStorages() {
        List<Storage> storages = storageService.getAllStorages();
        return new ResponseEntity<>(storages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Storage> getStorageById(@PathVariable long id) {
        Storage storage = storageService.getStorageById(id);
        return new ResponseEntity<>(storage, storage.getId() > 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createStorage(@RequestBody String name) {
        storageService.createStorage(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateStorageById(@RequestBody Storage storage) {
        storageService.updateStorage(storage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStorageById(@RequestParam long id) {
        storageService.deleteStorageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
