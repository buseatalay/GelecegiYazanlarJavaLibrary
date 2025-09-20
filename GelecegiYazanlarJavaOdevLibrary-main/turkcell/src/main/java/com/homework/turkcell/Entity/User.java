package com.homework.turkcell.Entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int Id;
    private String Username;
    private String Fullname;
    private boolean Admin;

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}


