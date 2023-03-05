package com.chocolatestore.controller;

import com.chocolatestore.domain.Manufacturer;
import com.chocolatestore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getAllmanufacturers(ModelMap modelMap) {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        modelMap.addAttribute("manufacturers", manufacturers);
        return "allManufacturers";
    }

    @GetMapping("/{id}")
    public String getManufacturerById(@PathVariable long id, ModelMap modelMap) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        modelMap.addAttribute("manufacturer", manufacturer);
        return "manufacturer";
    }

    // TODO: 26.02.2023 add validation
    @PostMapping
    public String createManufacturer(@RequestParam String name) {
        int result = manufacturerService.createManufacturer(name);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @PutMapping
    @ResponseBody
    public String updateManufacturerById(@ModelAttribute Manufacturer manufacturer) {
        int result = manufacturerService.updateManufacturer(manufacturer);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteManufacturerById(@PathVariable long id) {
        int result = manufacturerService.deleteManufacturerById(id);
        return result > 0 ? "successful" : "unsuccessful";
    }
}
