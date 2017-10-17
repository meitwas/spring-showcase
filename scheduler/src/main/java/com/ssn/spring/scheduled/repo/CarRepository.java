package com.ssn.spring.scheduled.repo;

import com.ssn.spring.scheduled.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
