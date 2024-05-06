package com.example.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "brandType")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandType {
    @Id
    private String id;

    private String name;
}
