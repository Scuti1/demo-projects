package com.example.back.repository;

import com.example.back.model.Car;
import com.example.back.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface CarRepository extends MongoRepository<Car,String> {

    List<Car> findAll();

    Car findFirstById(String id);
}
