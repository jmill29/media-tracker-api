package com.jmill29.tvtrackerapi.exception;

public class ShowNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public ShowNotFoundException() {
        super("Show not found");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public ShowNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public ShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public ShowNotFoundException(Throwable cause) {
        super(cause);
    }

}
