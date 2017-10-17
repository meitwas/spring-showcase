package com.ssn.spring.scheduled.service;

import com.ssn.spring.scheduled.entity.Car;
import com.ssn.spring.scheduled.repo.CarRepository;
import org.joda.time.DateTime;
import org.joda.time.PeriodType;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {
    final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Car> findAll() {
        List<Car> contacts = new ArrayList<>();
        carRepository.findAll().forEach(contacts::add);

        return contacts;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
//    @Scheduled(fixedDelay=10000)
    public void updateCarAgeJob() {
        List<Car> cars = findAll();

        DateTime currentDate = DateTime.now();
        logger.info("Car age update job started");

        for (Car car: cars) {
            int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();

            car.setAge(age);
            save(car);
            logger.info("Car age update--- " + car);
        }

        logger.info("Car age update job completed successfully");
    }
}

