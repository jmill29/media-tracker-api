package com.jmill29.tvtrackerapi.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing a user in the system.
 * Used to transfer user data between layers without exposing sensitive information.
 */
public class UserDto {
    /** The unique ID of the user */
    private int userId;
    /** The full name of the user */
    private String name;
    /** The username of the user */
    private String username;
    /** The email address of the user */
    private String email;
    /** The date and time the user was created */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public UserDto() {
    }

    /**
     * Constructs a UserDto with all fields.
     * @param userId the user's unique ID
     * @param name the user's full name
     * @param username the user's username
     * @param email the user's email address
     * @param createdAt the date and time the user was created
     */
    public UserDto(int userId, String name, String username, String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    /**
     * Gets the user's unique ID.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique ID.
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's full name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email address.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the date and time the user was created.
     * @return the creation date and time
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the date and time the user was created.
     * @param createdAt the creation date and time
     */
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
