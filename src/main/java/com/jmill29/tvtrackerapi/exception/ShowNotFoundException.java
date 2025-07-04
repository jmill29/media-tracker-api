package com.jmill29.tvtrackerapi.exception;

/**
 * Exception thrown to indicate that a requested show could not be found in the database or system.
 * <p>
 * This exception is typically used in the service or DAO layers when a show lookup by ID, title,
 * or other criteria fails.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When a user requests details for a show that does not exist.</li>
 *   <li>When attempting to update or delete a show that cannot be found.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
public class ShowNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code ShowNotFoundException} with a default message indicating the show was not found.
     */
    public ShowNotFoundException() {
        super("Show not found");
    }

    /**
     * Constructs a new {@code ShowNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public ShowNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ShowNotFoundException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public ShowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ShowNotFoundException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public ShowNotFoundException(Throwable cause) {
        super(cause);
    }
}
