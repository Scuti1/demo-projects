package com.example.back.controller;

import com.example.back.model.Car;
import com.example.back.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @RequestMapping("/getCar")
    public ResponseEntity<Car> getCar(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(carRepository.findFirstById(id));
    }

    @RequestMapping("/getAllCar")
    public ResponseEntity<List<Car>> getAllCar() {
        return ResponseEntity.ok(carRepository.findAll());
    }
}
