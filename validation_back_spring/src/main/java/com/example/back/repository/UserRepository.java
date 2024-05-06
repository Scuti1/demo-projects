package com.example.back.repository;

import com.example.back.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{email :?0}")
    User findByEmail(String email);

    List<User> findAll();

}
