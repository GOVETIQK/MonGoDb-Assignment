package com.demo.mongo.database.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddDatabaseDTO {

    @NotNull(message = "{host.notnull}")
    @NotEmpty(message = "{host.notempty}")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "{host.notvalid}")
    String host;

    Integer port;

    @NotNull(message = "{name.notnull}")
    @NotEmpty(message = "{name.notempty}")
    @Pattern(regexp = "^([A-Za-z0-9_])+$", message = "{name.notvalid}")
    String name;

    @NotNull(message = "{username.notnull}")
    @NotEmpty(message = "{username.notempty}")
    @Pattern(regexp = "^([A-Za-z0-9_])+$", message = "{username.notvalid}")
    String username;

    @NotNull(message = "{password.notnull}")
    @NotEmpty(message = "{password.notempty}")
    String password;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "{" + " host='" + getHost() + "'" + ", port='" + getPort() + "'" + ", name ='" + getName() + "'"
                + ", username='" + getUsername() + "'" + ", password='" + getPassword() + "'" + "}";
    }

    public Database toEntity() {
        return new Database(name, host, port, username, password);

    }

}