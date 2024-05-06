package com.example.back.repository;

import com.example.back.model.BrandType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface BrandTypeRepository extends MongoRepository<BrandType,String> {
    List<BrandType> findAll();
}
