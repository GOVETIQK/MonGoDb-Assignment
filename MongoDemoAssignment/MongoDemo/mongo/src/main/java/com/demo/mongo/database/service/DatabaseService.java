package com.demo.mongo.database.service;

import java.util.List;
import java.util.Map;
import com.demo.mongo.database.model.AddDatabaseDTO;

public interface DatabaseService {

    public String addDBServer(AddDatabaseDTO database);

    public List<String> getAllDatabases(String dbServerName);

    public Map<String, Object> getAllTables(String dbServerName, String databaseName, String tableType);

}