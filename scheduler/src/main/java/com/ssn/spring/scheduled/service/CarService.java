package com.ssn.spring.scheduled.service;

import com.ssn.spring.scheduled.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
    void updateCarAgeJob();
}
