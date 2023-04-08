package com.chocolatestore.controller;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

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
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        List<ManufacturerDTO> md = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(md, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable long id) {
        ManufacturerDTO md = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(md, md != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createManufacturer(@RequestParam String manufacturerName) {
        Manufacturer m = manufacturerService.createManufacturer(manufacturerName);
        return new ResponseEntity<>(m != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateManufacturerById(@PathVariable long id, @RequestParam String manufacturerName) {
        Manufacturer m = manufacturerService.updateManufacturer(id, manufacturerName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> removeManufacturerById(@PathVariable long id) {
        boolean result = manufacturerService.removeManufacturerById(id);
        return new ResponseEntity<>(result ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);
    }
}
