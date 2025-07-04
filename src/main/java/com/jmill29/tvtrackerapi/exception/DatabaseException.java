package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate a failure during a database operation in the application.
 * <p>
 * This exception is typically used in the service or DAO layers to signal that a database query,
 * update, or transaction could not be completed successfully. It is annotated with
 * {@link org.springframework.web.bind.annotation.ResponseStatus} to automatically return a
 * 500 Internal Server Error ({@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR})
 * when thrown in a Spring web context.
 * <p>
 * Usage examples include:
 * <ul>
 *   <li>When a database connection cannot be established.</li>
 *   <li>When a SQL query fails or returns an unexpected result.</li>
 *   <li>When a transaction is rolled back due to an error.</li>
 * </ul>
 * This helps provide a clear and consistent error response to API consumers and aids in debugging.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends RuntimeException {

    /**
     * Constructs a new {@code DatabaseException} with a default error message indicating a database operation failure.
     */
    public DatabaseException() {
        super("Database operation failed");
    }

    /**
     * Constructs a new {@code DatabaseException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the database error
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code DatabaseException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the database error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code DatabaseException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
