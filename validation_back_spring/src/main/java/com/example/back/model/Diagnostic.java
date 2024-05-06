package com.example.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diagnostic")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Diagnostic {
    @Id
    private String id;
    private String description;
    private String fileUrl;
    @DBRef
    private Car car;
    @DBRef
    private User user;
}
