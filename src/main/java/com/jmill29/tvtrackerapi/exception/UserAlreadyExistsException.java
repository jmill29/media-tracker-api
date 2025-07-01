package com.jmill29.tvtrackerapi.exception;

/**
 * Exception thrown when attempting to create a user that already exists in the system.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public UserAlreadyExistsException() {
        super("User already exists");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
