package com.jmill29.tvtrackerapi.dto;

/**
 * Data Transfer Object representing a user's watch history for a show.
 * Used to transfer show and watch status information for a user.
 */
public class UserWatchHistoryResponse {

    /** The unique ID of the show */
    private int showId;
    /** The name of the show */
    private String showName;
    /** The description of the show */
    private String description;
    /** The image URL for the show */
    private String imageUrl;
    /** The user's watch status for the show (e.g., "watched", "watching", "plan to watch") */
    private String status;

    /**
     * Default constructor.
     */
    public UserWatchHistoryResponse() {
    }

    /**
     * Constructs a UserWatchHistoryDto with all fields.
     * @param showId the unique ID of the show
     * @param showName the name of the show
     * @param description the description of the show
     * @param imageUrl the image URL for the show
     * @param status the user's watch status for the show
     */
    public UserWatchHistoryResponse(int showId, String showName, String description, String imageUrl, String status) {
        this.showId = showId;
        this.showName = showName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserWatchHistoryDto{");
        sb.append("showId=").append(showId);
        sb.append(", showName=").append(showName);
        sb.append(", description=").append(description);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

}
