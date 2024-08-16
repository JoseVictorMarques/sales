package com.example.sales.business;

import com.example.sales.model.dtos.CarDTO;
import com.example.sales.model.entities.Car;
import com.example.sales.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBusiness {
    @Autowired
    private CarRepository repository;

    public CarDTO createCar(CarDTO carDTO){
        Car car = carDTO.toEntiy();
        car = repository.save(car);
        return car.toDTO();
    }
}
