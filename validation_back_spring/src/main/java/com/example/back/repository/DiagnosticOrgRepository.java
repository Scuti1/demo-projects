package com.example.back.repository;

import com.example.back.model.DiagnosticOrg;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan
public interface DiagnosticOrgRepository extends MongoRepository<DiagnosticOrg,String> {

}
