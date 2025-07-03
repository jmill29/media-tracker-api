package com.jmill29.tvtrackerapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the watch status of a show for a user.
 * Each status has a string value used for database storage and retrieval.
 */
public enum WatchStatus {
    /** The user has not watched the show */
    NOT_WATCHED("Not Watched"),
    /** The user wants to watch the show */
    WANT_TO_WATCH("Want to Watch"),
    /** The user is currently watching the show */
    CURRENTLY_WATCHING("Currently Watching"),
    /** The user has already watched the show */
    ALREADY_WATCHED("Already Watched");

    /** The string value stored in the database */
    private final String dbValue;

    /**
     * Constructs a WatchStatus enum with the given database value.
     * @param dbValue the string value for the database
     */
    WatchStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    /**
     * Gets the string value used for database storage.
     * @return the database value
     */
    @JsonValue
    public String getDbValue() {
        return dbValue;
    }

    /**
     * Returns the WatchStatus enum corresponding to the given database value.
     * @param value the string value from the database
     * @return the corresponding WatchStatus
     * @throws IllegalArgumentException if the value does not match any status
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

