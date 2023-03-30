package com.chocolatestore.service;

import com.chocolatestore.domain.DTO.ManufacturerDTO;
import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.mappers.HibernateDTOMapper;
import com.chocolatestore.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerService {

    ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public ArrayList<ManufacturerDTO> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        List<ManufacturerDTO> manufacturersDTO = new ArrayList<>();
        for (Manufacturer manufacturer :
                manufacturers) {
            manufacturersDTO.add(HibernateDTOMapper.getManufacturerDTO(manufacturer));
        }
        return (ArrayList<ManufacturerDTO>) manufacturersDTO;
    }

    public ManufacturerDTO getManufacturerById(long id) {
        return HibernateDTOMapper.getManufacturerDTO(manufacturerRepository.findById(id).get());
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.saveAndFlush(manufacturer);
    }

    public void deleteManufacturerById(long id) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        manufacturerRepository.deleteById(id);
    }

    public void deleteManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.delete(manufacturer);
    }
}