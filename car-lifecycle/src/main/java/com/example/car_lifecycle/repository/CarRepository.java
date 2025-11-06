/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.car_lifecycle.repository;

import com.example.car_lifecycle.model.car;
import com.example.car_lifecycle.model.CarStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CarRepository extends MongoRepository<car, String> {

    List<car> findByStatus(CarStatus status);

    List<car> findByModelIgnoreCase(String model);

    List<car> findByPriceBetween(double min, double max);

    // ✅ NEW
    List<car> findByBrandIgnoreCase(String brand);
}