package com.jmill29.tvtrackerapi.model;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

/**
 * Represents a user's watch history entry for a specific TV show.
 * <p>
 * Each entry contains a user ID, a show ID, and a {@link WatchStatus}
 * that indicates the user's current viewing status for the show.
 * </p>
 */
public class UserWatchHistory {

    /** The unique ID of the user */
    private int userId;

    /** The unique ID of the show */
    private int showId;

    /** The user's current watch status for the show */
    private WatchStatus status;

    /**
     * Default no-args constructor.
     */
    public UserWatchHistory() {
    }

    /**
     * Constructs a new {@code UserWatchHistory} entry with all fields.
     *
     * @param userId  the ID of the user
     * @param showId  the ID of the show
     * @param status  the user's watch status for the show
     */
    public UserWatchHistory(int userId, int showId, WatchStatus status) {
        this.userId = userId;
        this.showId = showId;
        this.status = status;
    }

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the show ID.
     *
     * @return the show ID
     */
    public int getShowId() {
        return showId;
    }

    /**
     * Sets the show ID.
     *
     * @param showId the show ID
     */
    public void setShowId(int showId) {
        this.showId = showId;
    }

    /**
     * Returns the watch status for this entry.
     *
     * @return the watch status
     */
    public WatchStatus getStatus() {
        return status;
    }

    /**
     * Sets the watch status for this entry.
     *
     * @param status the new watch status
     */
    public void setStatus(WatchStatus status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the UserWatchHistory object.
     *
     * @return a string with user ID, show ID, and status
     */
    @Override
    public String toString() {
        return "UserWatchHistory{" +
                "userId=" + userId +
                ", showId=" + showId +
                ", status=" + status +
                '}';
    }

    /**
     * Compares this object to another for equality based on user ID, show ID, and status.
     *
     * @param o the object to compare
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserWatchHistory)) return false;

        UserWatchHistory that = (UserWatchHistory) o;

        if (userId != that.userId) return false;
        if (showId != that.showId) return false;
        return status == that.status;
    }

    /**
     * Generates a hash code based on user ID, show ID, and status.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + showId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
