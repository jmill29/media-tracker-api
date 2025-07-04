package com.jmill29.tvtrackerapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the watch status of a show for a user.
 * <p>
 * Each status corresponds to a human-readable string used for database storage and retrieval.
 * </p>
 */
public enum WatchStatus {

    /** The user has not watched the show. */
    NOT_WATCHED("Not Watched"),

    /** The user wants to watch the show. */
    WANT_TO_WATCH("Want to Watch"),

    /** The user is currently watching the show. */
    CURRENTLY_WATCHING("Currently Watching"),

    /** The user has already watched the show. */
    ALREADY_WATCHED("Already Watched");

    /** The string value stored in the database. */
    private final String dbValue;

    /**
     * Constructs a {@code WatchStatus} with the given database value.
     *
     * @param dbValue the string value used for storage
     */
    WatchStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    /**
     * Returns the string value to be used in JSON serialization and database storage.
     *
     * @return the database-compatible string representation
     */
    @JsonValue
    public String getDbValue() {
        return dbValue;
    }

    /**
     * Parses the given string to return the corresponding {@code WatchStatus} enum constant.
     * <p>
     * This method is used during JSON deserialization.
     * </p>
     *
     * @param value the string value from the database or request
     * @return the matching {@code WatchStatus} enum
     * @throws IllegalArgumentException if no matching status is found
     */
    @JsonCreator
    public static WatchStatus fromDbValue(String value) {
        for (WatchStatus status : values()) {
            if (status.dbValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid watch status: " + value);
    }
}
