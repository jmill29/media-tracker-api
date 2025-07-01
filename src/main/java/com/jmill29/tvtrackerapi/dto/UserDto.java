package com.jmill29.tvtrackerapi.dto;

import java.time.LocalDateTime;

public class UserDto {
    private int userId;
    private String name;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public UserDto() {
    }

    public UserDto(String email, String name, int userId, String username, LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
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
        sb.append("UserDto{");
        sb.append("userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }

}
