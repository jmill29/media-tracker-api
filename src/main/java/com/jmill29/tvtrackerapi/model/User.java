package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

/**
 * Model representing a user in the system.
 */
public class User {

    /** The unique ID of the user */
    private int userId;
    /** The full name of the user */
    private String name;
    /** The username of the user */
    private String username;
    /** The user's password (should be stored as a hash) */
    private String password;
    /** The email address of the user */
    private String email;
    /** The date and time the user was created in the system */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs a User with all fields.
     * @param userId the unique ID of the user
     * @param name the user's full name
     * @param username the user's username
     * @param password the user's password (should be hashed)
     * @param email the user's email address
     * @param createdAt the date and time the user was created
     */
    public User(int userId, String name, String username, String password, String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
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

    /**
     * Returns a string representation of the User object.
     * Useful for logging and debugging purposes.
     * @return a string representation of the user
     */
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
