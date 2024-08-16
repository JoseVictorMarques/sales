package com.example.sales.controller;

import com.example.sales.business.CarBusiness;
import com.example.sales.model.dtos.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    CarBusiness carBusiness;

    @PostMapping("/create-car")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carBusiness.createCar(carDTO));
    }
}
