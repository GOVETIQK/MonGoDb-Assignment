package com.demo.mongo.database.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.mongo.database.model.AddDatabaseDTO;
import com.demo.mongo.database.model.Database;
import com.demo.mongo.database.repository.DatabaseRepository;
import com.demo.mongo.util.exception.ApplicationLogicError;
import com.demo.mongo.util.exception.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseRepository databaseRepo;

    @Override
    public String addDBServer(AddDatabaseDTO database) {
        try {
            databaseRepo.insert(database.toEntity());
            return "DbServer Added Successfully";
        } catch (Exception e) {
            throw new ApplicationLogicError(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getAllDatabases(String dbServerName) throws BadRequestException, ApplicationLogicError {
        List<String> databases = new ArrayList<>();

        Database database = databaseRepo.findById(dbServerName).orElse(null);
        if (database == null) {
            throw new BadRequestException("DbServerName Not Found");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://" + database.getConnectionUrl(null),
                    database.getUsername(), database.getPassword());

            PreparedStatement ps = connection
                    .prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                databases.add(rs.getString(1));
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception ex) {
            throw new ApplicationLogicError(ex.getMessage(), ex);
        }
        return databases;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getAllTables(String dbServerName, String databaseName, String tableType)
            throws BadRequestException, ApplicationLogicError {
        Map<String, Object> allTabes = new HashMap<>();

        Database database = databaseRepo.findById(dbServerName).orElse(null);
        if (database == null) {
            throw new BadRequestException("DbServerName Not Found");
        }

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + database.getConnectionUrl(databaseName), database.getUsername(),
                    database.getPassword());

            String query = "SELECT table_schema,table_name FROM information_schema.tables;";
            if (tableType != null) {
                query = query.replace(";", " where table_type = '" + tableType + "';");
            }

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (allTabes.get(rs.getString(1)) == null) {
                    List<String> schemaTables = new ArrayList<>();
                    schemaTables.add(rs.getString(2));
                    allTabes.put(rs.getString(1), schemaTables);
                } else {
                    List<String> schemaTables = (List<String>) allTabes.get(rs.getString(1));
                    schemaTables.add(rs.getString(2));
                    allTabes.put(rs.getString(1), schemaTables);
                }
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception ex) {
            throw new ApplicationLogicError(ex.getMessage(), ex);
        }
        return allTabes;
    }

}