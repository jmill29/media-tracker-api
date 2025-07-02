package com.jmill29.tvtrackerapi.model;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

public class UserWatchHistory {

    private int userId;
    private int showId;
    private WatchStatus status;

    public UserWatchHistory() {
    }

    public UserWatchHistory(int userId, int showId, WatchStatus status) {
        this.userId = userId;
        this.showId = showId;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public WatchStatus getStatus() {
        return this.status;
    }

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
