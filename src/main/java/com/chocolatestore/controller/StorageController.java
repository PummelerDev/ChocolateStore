package com.chocolatestore.controller;

import com.chocolatestore.domain.Storage;
import com.chocolatestore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getAllStorages(ModelMap modelMap) {
        List<Storage> storages = storageService.getAllStorages();
        modelMap.addAttribute("storages", storages);
        return "allStorages";
    }

    @GetMapping("/{id}")
    public String getStorageById(@PathVariable long id, ModelMap modelMap) {
        Storage storage = storageService.getStorageById(id);
        modelMap.addAttribute("storage", storage);
        return "storage";
    }

    // TODO: 27.02.2023 add validation
    @PostMapping
    public String createStorage(@RequestParam String name) {
        int result = storageService.createStorage(name);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @PutMapping
    @ResponseBody
    public String updateStorageById(@ModelAttribute Storage storage) {
        int result = storageService.updateStorage(storage);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteStorageById(@RequestParam long id) {
        int result = storageService.deleteStorageById(id);
        return result > 0 ? "successful" : "unsuccessful";
    }
}
