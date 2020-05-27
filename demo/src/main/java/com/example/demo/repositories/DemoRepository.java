package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entities.Demo;

public interface DemoRepository extends MongoRepository<Demo, Integer> {
	@Query("{ 'content' : { $regex : ?0 } }")
    public List<Demo> searchByContent(String content);
}