/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.car_lifecycle.service;

import com.example.car_lifecycle.exception.CarNotFoundException;
import com.example.car_lifecycle.model.car;
import com.example.car_lifecycle.model.CarStatus;
import com.example.car_lifecycle.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repo;

    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public car save(car car) {
        return repo.save(car);
    }

    public List<car> getAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<car> findByStatus(CarStatus status) {
        return repo.findByStatus(status);
    }

    public List<car> findByModel(String model) {
        return repo.findByModelIgnoreCase(model);
    }

    public List<car> findByPriceRange(double min, double max) {
        if (min > max) {
            double temp = min; min = max; max = temp;
        }
        return repo.findByPriceBetween(min, max);
    }

    // ✅ NEW
    public car getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
    }

    // ✅ NEW
    public List<car> findByBrand(String brand) {
        return repo.findByBrandIgnoreCase(brand);
    }

    // ✅ NEW
    public Page<car> getPaged(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    // ✅ NEW
    public List<car> getSorted(String field) {
        return repo.findAll(Sort.by(field));
    }
}