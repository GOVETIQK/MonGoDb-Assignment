package com.demo.mongo.database.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import com.demo.mongo.database.model.AddDatabaseDTO;
import com.demo.mongo.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @PostMapping(value = "add/datapoint")
    public ResponseEntity<String> addDatabase(@Valid @RequestBody AddDatabaseDTO addDatabaseDTO) {
        return new ResponseEntity<String>(databaseService.addDBServer(addDatabaseDTO), HttpStatus.OK);
    }

    @GetMapping(value = "v1/{dBServerName}/fetch/databases")
    public ResponseEntity<List<String>> getMethodName(@PathVariable("dBServerName") String dBServerName) {
        return new ResponseEntity<>(databaseService.getAllDatabases(dBServerName), HttpStatus.OK);

    }

    @GetMapping(value = "v1/{dBServerName}/db/{database}/fetch/tables")
    public ResponseEntity<Map<String, Object>> getMethodName12(@PathVariable("dBServerName") String dBServerName,
            @PathVariable("database") String database,
            @RequestParam(value = "tableType", required = false) String tableType) {
        return new ResponseEntity<>(databaseService.getAllTables(dBServerName, database, tableType), HttpStatus.OK);
    }
}