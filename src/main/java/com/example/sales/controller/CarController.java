package com.example.sales.controller;

import com.example.sales.business.CarBusiness;
import com.example.sales.model.dtos.CarDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    CarBusiness carBusiness;

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carBusiness.createCar(carDTO));
    }

    @GetMapping("/list-cars")
    public ResponseEntity<List<CarDTO>> listCars(){
        return ResponseEntity.ok(carBusiness.listCars());
    }
}
