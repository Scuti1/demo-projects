package com.example.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fuelType")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuelType {
    @Id
    private String id;
    private String name;
}
