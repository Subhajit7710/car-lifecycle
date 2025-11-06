/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.car_lifecycle.controller;

import com.example.car_lifecycle.model.car;
import com.example.car_lifecycle.model.CarStatus;
import com.example.car_lifecycle.service.CarService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    // ✅ Create
    @PostMapping
    public car add(@Valid @RequestBody car car) {
        return service.save(car);
    }

    // ✅ Update
    @PutMapping("/{id}")
    public car updateCar(@PathVariable String id, @Valid @RequestBody car car) {
        car.setId(id);
        return service.save(car);
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        service.delete(id);
    }

    // ✅ Get all
    @GetMapping
    public List<car> getAll() {
        return service.getAll();
    }

    // ✅ Find by ID
    @GetMapping("/{id}")
    public car getById(@PathVariable String id) {
        return service.getById(id);
    }

    // ✅ Find by Status
    @GetMapping("/status/{status}")
    public List<car> getByStatus(@PathVariable CarStatus status) {
        return service.findByStatus(status);
    }

    // ✅ Find by Model
    @GetMapping("/model/{model}")
    public List<car> getByModel(@PathVariable String model) {
        return service.findByModel(model);
    }

    // ✅ Find by Brand
    @GetMapping("/brand/{brand}")
    public List<car> getByBrand(@PathVariable String brand) {
        return service.findByBrand(brand);
    }

    // ✅ Price Range Filter
    // Example: /api/cars/price?min=20000&max=50000
    @GetMapping("/price")
    public List<car> getByPriceRange(@RequestParam double min,
                                     @RequestParam double max) {
        return service.findByPriceRange(min, max);
    }

    // ✅ Pagination
    // Example: /api/cars/paged?page=0&size=5
    @GetMapping("/paged")
    public Page<car> getPaged(@RequestParam int page,
                              @RequestParam int size) {
        return service.getPaged(page, size);
    }

    // ✅ Sorting
    // Example: /api/cars/sort?field=price
    @GetMapping("/sort")
    public List<car> getSorted(@RequestParam String field) {
        return service.getSorted(field);
    }
}