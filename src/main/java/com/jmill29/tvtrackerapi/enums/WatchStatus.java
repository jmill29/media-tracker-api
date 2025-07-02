package com.jmill29.tvtrackerapi.enums;

public enum WatchStatus {
    NOT_WATCHED("Not Watched"),
    WANT_TO_WATCH("Want to Watch"),
    CURRENTLY_WATCHING("Currently Watching"),
    ALREADY_WATCHED("Already Watched");

    private final String dbValue;

    WatchStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static WatchStatus fromDbValue(String value) {
        for (WatchStatus status : values()) {
            if (status.dbValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid watch status: " + value);
    }
}

