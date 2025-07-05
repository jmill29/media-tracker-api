package com.jmill29.tvtrackerapi.dto;

public class UserRequest {

    /** The full name of the user */
    private String name;
    /** The username of the user */
    private String username;
    /** The user's password (should be stored as a hash) */
    private String password;
    /** The email address of the user */
    private String email;

    /**
     * Default constructor.
     */
    public UserRequest() {}

    /**
     * Constructs a {@code UserRequest} with all fields initialized.
     * @param name the user's full name
     * @param username the user's username
     * @param password the user's password (should be hashed)
     * @param email the user's email address
     */
    public UserRequest(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    /**
     * Gets the user's full name.
     *
     * @return the user's full name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the user's full name.
     *
     * @param name the user's full name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Sets the user's username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Gets the user's password (should be hashed before storage).
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets the user's password (should be hashed before storage).
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }


    /**
     * Sets the user's email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Returns a string representation of the UserRequest object, with the password protected.
     *
     * @return a string representation of the user request
     */
    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", email='" + email + '\'' +
                '}';
    }

    

}
