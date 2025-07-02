package com.jmill29.tvtrackerapi.dto;

public class UserWatchHistoryDto {

    private int showId;
    private String showName;
    private String description;
    private String imageUrl;
    private String status;

    public UserWatchHistoryDto() {
    }

    public UserWatchHistoryDto(int showId, String showName, String description, String imageUrl, String status) {
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
