package com.demo.mongo.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "databases")
public class Database {

    @Id
    private String dbServerName;

    private String host;
    private Integer port;
    private String username;
    private String password;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbServerName() {
        return this.dbServerName;
    }

    public void setDbServerName(String dbServerName) {
        this.dbServerName = dbServerName;
    }

    @Override
    public String toString() {
        return "{" + " host='" + getHost() + "'" + ", port='" + getPort() + "'" + ", username='" + getUsername() + "'"
                + ", password='" + getPassword() + "'" + ", dbServerName='" + getDbServerName() + "'" + "}";
    }

    public Database(String dbServerName, String host, Integer port, String username, String password) {
        this.dbServerName = dbServerName;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getConnectionUrl(String database) {
        return getHost() + ":" + getPort() + "/" + (database != null ? database : "?");
    }
}
