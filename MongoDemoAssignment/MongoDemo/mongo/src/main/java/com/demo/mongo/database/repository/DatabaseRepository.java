package com.demo.mongo.database.repository;

import com.demo.mongo.database.model.Database;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseRepository extends MongoRepository<Database, String> {
}