package com.boostmytool.beststore.models;

public class User {
    private String username;
    private String role;
    private String password;

    public User(String username, String role, String password) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters (omitted for brevity)

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
