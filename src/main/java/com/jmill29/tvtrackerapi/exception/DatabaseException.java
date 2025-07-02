package com.jmill29.tvtrackerapi.exception;

public class DatabaseException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public DatabaseException() {
        super("Database operation failed");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }

}
