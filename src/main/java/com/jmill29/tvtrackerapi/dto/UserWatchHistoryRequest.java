package com.jmill29.tvtrackerapi.dto;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

public class UserWatchHistoryRequest {
    private int showId;
    private WatchStatus status;

    public UserWatchHistoryRequest() {
    }

    public UserWatchHistoryRequest(int showId, WatchStatus status) {
        this.showId = showId;
        this.status = status;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public WatchStatus getStatus() {
        return status;
    }

    public void setStatus(WatchStatus status) {
        this.status = status;
    }
}
