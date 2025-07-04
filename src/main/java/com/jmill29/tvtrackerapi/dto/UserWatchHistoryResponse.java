package com.jmill29.tvtrackerapi.dto;

/**
 * Data Transfer Object representing a user's watch history for a show.
 * <p>
 * Used to transfer show metadata and the user's current watch status.
 * </p>
 */
public class UserWatchHistoryResponse {

    /** The unique ID of the show. */
    private int showId;

    /** The name of the show. */
    private String showName;

    /** The description of the show. */
    private String description;

    /** The image URL for the show. */
    private String imageUrl;

    /** The user's watch status for the show (e.g., "Watched", "Watching", "Plan to Watch"). */
    private String status;

    /**
     * Default constructor.
     */
    public UserWatchHistoryResponse() {
    }

    /**
     * Constructs a {@code UserWatchHistoryResponse} with all fields.
     *
     * @param showId      the unique ID of the show
     * @param showName    the name of the show
     * @param description the description of the show
     * @param imageUrl    the image URL for the show
     * @param status      the user's watch status for the show
     */
    public UserWatchHistoryResponse(int showId, String showName, String description, String imageUrl, String status) {
        this.showId = showId;
        this.showName = showName;
        this.description = description;
        this.imageUrl = imageUrl;
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
     * Gets the name of the show.
     *
     * @return the show name
     */
    public String getShowName() {
        return showName;
    }

    /**
     * Sets the name of the show.
     *
     * @param showName the show name
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * Gets the description of the show.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the show.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image URL for the show.
     *
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL for the show.
     *
     * @param imageUrl the image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the user's watch status for the show.
     *
     * @return the watch status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the user's watch status for the show.
     *
     * @param status the watch status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserWatchHistoryResponse{" +
                "showId=" + showId +
                ", showName='" + showName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
