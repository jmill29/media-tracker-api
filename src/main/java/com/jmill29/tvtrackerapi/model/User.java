package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

public class User {

    private int userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(LocalDateTime createdAt, String email, String name, String password, int userId, String username) {
        this.createdAt = createdAt;
        this.email = email;
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }


}
