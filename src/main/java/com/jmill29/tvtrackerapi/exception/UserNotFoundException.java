package com.jmill29.tvtrackerapi.exception;

/**
 * Thrown to indicate that a user could not be found in the system.
 * <p>
 * This exception is typically used in service or DAO layers when
 * user retrieval operations fail.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public UserNotFoundException() {
        super("User not found");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}
