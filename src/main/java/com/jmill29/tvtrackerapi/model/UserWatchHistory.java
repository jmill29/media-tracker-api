package com.jmill29.tvtrackerapi.model;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

/**
 * Model representing a user's watch history for a specific show.
 * Contains the user ID, show ID, and the user's watch status for that show.
 */
public class UserWatchHistory {

    /** The unique ID of the user */
    private int userId;
    /** The unique ID of the show */
    private int showId;
    /** The user's watch status for the show */
    private WatchStatus status;

    /**
     * Default constructor.
     */
    public UserWatchHistory() {
    }

    /**
     * Constructs a UserWatchHistory with all fields.
     * @param userId the unique ID of the user
     * @param showId the unique ID of the show
     * @param status the user's watch status for the show
     */
    public UserWatchHistory(int userId, int showId, WatchStatus status) {
        this.userId = userId;
        this.showId = showId;
        this.status = status;
    }

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the show ID.
     * @return the show ID
     */
    public int getShowId() {
        return showId;
    }

    /**
     * Sets the show ID.
     * @param showId the show ID
     */
    public void setShowId(int showId) {
        this.showId = showId;
    }

    /**
     * Gets the user's watch status for the show.
     * @return the watch status
     */
    public WatchStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the user's watch status for the show.
     * @param status the watch status
     */
    public void setStatus(WatchStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserWatchHistory{" +
                "userId=" + userId +
                ", showId=" + showId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserWatchHistory)) return false;

        UserWatchHistory that = (UserWatchHistory) o;

        if (userId != that.userId) return false;
        if (showId != that.showId) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + showId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
