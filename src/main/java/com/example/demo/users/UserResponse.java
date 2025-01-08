package com.example.demo.users;

public class UserResponse {
    private Users user;
    private String token;

    // Constructor
    public UserResponse(Users user, String token) {
        this.user = user;
        this.token = token;
    }

    // Getters and Setters
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
