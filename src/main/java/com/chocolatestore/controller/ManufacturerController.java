package com.chocolatestore.controller;

import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<HttpStatus> createManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.createManufacturer(manufacturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateManufacturerById(@RequestBody Manufacturer manufacturer) {
        manufacturerService.updateManufacturer(manufacturer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteManufacturerById(@PathVariable long id) {
        manufacturerService.deleteManufacturerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
