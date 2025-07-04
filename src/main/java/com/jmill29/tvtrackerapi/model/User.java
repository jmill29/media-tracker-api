package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

/**
 * Represents a user within the TV tracker system.
 * <p>
 * This model is used to encapsulate user-related data, including personal details,
 * login credentials, and creation metadata.
 * </p>
 */
public class User {

    /** The unique identifier of the user */
    private int userId;

    /** The user's full name */
    private String name;

    /** The unique username chosen by the user */
    private String username;

    /** The user's hashed password */
    private String password;

    /** The user's email address */
    private String email;

    /** The timestamp when the user account was created */
    private LocalDateTime createdAt;

    /**
     * Default no-args constructor.
     */
    public User() {
    }

    /**
     * Constructs a new {@code User} with all fields initialized.
     *
     * @param userId    the unique ID of the user
     * @param name      the user's full name
     * @param username  the user's username
     * @param password  the user's hashed password
     * @param email     the user's email address
     * @param createdAt the account creation timestamp
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
     * Returns a string representation of the User object, excluding the password for security.
     * Useful for logging and debugging purposes.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
