package com.jmill29.tvtrackerapi.dto;

/**
 * Represents a standard structure for error responses returned by the API.
 * <p>
 * This class is typically used in exception handlers to provide clients with
 * consistent and descriptive error information.
 * </p>
 */
public class ErrorResponse {

    /**
     * The HTTP status code associated with the error (e.g., 400, 404, 500).
     */
    private int status;

    /**
     * A human-readable description of the error.
     */
    private String message;

    /**
     * The timestamp of when the error occurred, represented in milliseconds since epoch.
     */
    private long timestamp;

    /**
     * Default constructor.
     */
    public ErrorResponse() {
    }

    /**
     * Constructs an {@code ErrorResponse} with the specified details.
     * The parameter order matches the field declaration order: status, message, timestamp.
     *
     * @param status    the HTTP status code
     * @param message   the error message
     * @param timestamp the time the error occurred (in milliseconds since epoch)
     */
    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }


    /**
     * Gets the HTTP status code.
     *
     * @return the status code
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the HTTP status code.
     *
     * @param status the status code
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the error message.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Gets the timestamp of the error in milliseconds since epoch.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp of the error in milliseconds since epoch.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
