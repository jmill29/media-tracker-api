package com.jmill29.tvtrackerapi.dto;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

/**
 * Request DTO for adding or updating a user's watch history for a show.
 * <p>
 * Contains the show's ID and the desired watch status.
 * </p>
 */
public class UserWatchHistoryRequest {

    /** The unique ID of the show. */
    private int showId;

    /** The user's watch status for the show. */
    private WatchStatus status;

    /**
     * Default constructor.
     */
    public UserWatchHistoryRequest() {
    }

    /**
     * Constructs a {@code UserWatchHistoryRequest} with all fields.
     *
     * @param showId the unique ID of the show
     * @param status the user's watch status for the show
     */
    public UserWatchHistoryRequest(int showId, WatchStatus status) {
        this.showId = showId;
        this.status = status;
    }

    /**
     * Gets the unique ID of the show.
     *
     * @return the show ID
     */
    public int getShowId() {
        return showId;
    }

    /**
     * Sets the unique ID of the show.
     *
     * @param showId the show ID
     */
    public void setShowId(int showId) {
        this.showId = showId;
    }

    /**
     * Gets the user's watch status for the show.
     *
     * @return the watch status
     */
    public WatchStatus getStatus() {
        return status;
    }

    /**
     * Sets the user's watch status for the show.
     *
     * @param status the watch status
     */
    public void setStatus(WatchStatus status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "UserWatchHistoryRequest{" +
                "showId=" + showId +
                ", status=" + (status != null ? status.name() : null) +
                '}';
    }
}
