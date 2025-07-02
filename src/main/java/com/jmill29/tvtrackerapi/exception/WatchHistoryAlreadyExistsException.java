package com.jmill29.tvtrackerapi.exception;

public class WatchHistoryAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public WatchHistoryAlreadyExistsException() {
        super("Watch history entry already exists for this user and show");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public WatchHistoryAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public WatchHistoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public WatchHistoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
