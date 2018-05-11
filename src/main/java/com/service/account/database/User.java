package com.service.account.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "accountservice")
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private String email;
    private List<String> devices;

    public User(){}

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.devices = new ArrayList<>();
    }

    public User(String login, String password, String email, List<String> devices) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.devices = devices;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public boolean checkID(){
        return id.equals("");
    }
}
