package com.chocolatestore.service;

import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManufacturerService {

    ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ArrayList<Manufacturer> getAllManufacturers() {
           return manufacturerRepository.getAllManufacturers();
    }

    public Manufacturer getManufacturerById(long id) {
        return manufacturerRepository.getManufacturerById(id);
    }

    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.createManufacturer(manufacturer);
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.updateManufacturer(manufacturer);
    }

    public void deleteManufacturerById(long id) {
       Manufacturer manufacturer = new Manufacturer();
       manufacturer.setId(id);
       manufacturerRepository.deleteManufacturerById(manufacturer);
    }
}