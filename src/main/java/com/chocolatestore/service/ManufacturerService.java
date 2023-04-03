package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.mappers.ManufacturerMapper;
import com.chocolatestore.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository, ManufacturerMapper manufacturerMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    public ArrayList<ManufacturerDTO> getAllManufacturers() {
        return (ArrayList<ManufacturerDTO>) manufacturerRepository
                .findAll()
                .stream()
                .map(manufacturerMapper::mapManufacturerToManufacturerDTO)
                .collect(Collectors.toList());
    }

    public ManufacturerDTO getManufacturerById(long id) {
        return manufacturerMapper.mapManufacturerToManufacturerDTO(manufacturerRepository.findById(id).get());
    }

    public Manufacturer createManufacturer(String manufacturerName) {
        Manufacturer m = new Manufacturer();
        m.setName(manufacturerName);
        return manufacturerRepository.save(m);
    }

    public Manufacturer updateManufacturer(long id, String manufacturerName) {
        Manufacturer fromDB = manufacturerRepository.findById(id).get();
        Manufacturer intoDB = new Manufacturer();
        intoDB.setId(id);
        intoDB.setName(manufacturerName.isBlank()? fromDB.getName() : manufacturerName);
        return manufacturerRepository.saveAndFlush(intoDB);
    }

    public boolean deleteManufacturerById(long id) {
        manufacturerRepository.deleteById(id);
        return !manufacturerRepository.existsById(id);
    }

    public void deleteManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.delete(manufacturer);
    }
}