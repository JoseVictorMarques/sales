package com.example.sales.business;

import com.example.sales.model.dtos.CarDTO;
import com.example.sales.model.entities.Car;
import com.example.sales.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBusiness {
    private static final Logger logger = LoggerFactory.getLogger(CarBusiness.class);

    @Autowired
    private CarRepository repository;

    public CarDTO createCar(CarDTO carDTO){
        logger.info("Creating new car: {} - {}", carDTO.getDescription(), carDTO.getColor());
        Car car = carDTO.toEntity();
        car = repository.save(car);
        logger.info("Car created successfully with ID: {}", car.getId());
        return car.toDTO();
    }
}
