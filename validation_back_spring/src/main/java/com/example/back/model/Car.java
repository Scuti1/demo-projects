package com.example.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "car")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    @Id
    private String id;

    private String name;

    private BigDecimal price;
    private String motorPower;
    private String color;
    private String salonColor;
    private int comeYear;
    private int year;
    private int seatNumber;
    private int doorNumber;
    private String carNumber;
    private String motorNumber;
    @DBRef
    private BrandType brandType;
    @DBRef
    private FuelType fuelType;
    @DBRef
    private UserPublicInfo user;
    @DBRef
    private List<Diagnostic> diagnosticList;
}
