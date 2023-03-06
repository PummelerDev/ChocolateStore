package com.chocolatestore.controller;

import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable long id) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(manufacturer, manufacturer.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public ResponseEntity<HttpStatus> createManufacturer(@RequestParam String name) {
        int result = manufacturerService.createManufacturer(name);
        return new ResponseEntity<>(result > 0 ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> updateManufacturerById(@ModelAttribute Manufacturer manufacturer) {
        int result = manufacturerService.updateManufacturer(manufacturer);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteManufacturerById(@PathVariable long id) {
        int result = manufacturerService.deleteManufacturerById(id);
        return new ResponseEntity<>(result > 0 ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
