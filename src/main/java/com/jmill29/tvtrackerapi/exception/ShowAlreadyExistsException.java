package com.jmill29.tvtrackerapi.exception;

public class ShowAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public ShowAlreadyExistsException() {
        super("Show already exists");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public ShowAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public ShowAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public ShowAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
