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
        int result = storageService.createStorage(name);
        return new ResponseEntity<>(result > 0 ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateStorageById(@RequestBody Storage storage) {
        int result = storageService.updateStorage(storage);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStorageById(@RequestParam long id) {
        int result = storageService.deleteStorageById(id);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
