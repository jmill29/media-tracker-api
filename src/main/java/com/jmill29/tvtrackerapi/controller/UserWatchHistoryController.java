package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.service.UserWatchHistoryService;
import com.jmill29.tvtrackerapi.utils.AuthUtil;

@RestController
@RequestMapping("/api/watch-history")
public class UserWatchHistoryController {

    private final UserWatchHistoryService userWatchHistoryService;

    @Autowired
    public UserWatchHistoryController(UserWatchHistoryService userWatchHistoryService) {
        this.userWatchHistoryService = userWatchHistoryService;
    }

    // Define endpoints for user watch history operations here
    @GetMapping
    public ResponseEntity<List<UserWatchHistoryResponse>> getWatchHistoryByUsername(
        @RequestParam(required = false, defaultValue = "false") boolean getAll,
        @RequestHeader("Authorization") String authHeader) {
        List<UserWatchHistoryResponse> watchHistory = userWatchHistoryService.getWatchHistoryByUsername(
            AuthUtil.extractUsernameFromAuthHeader(authHeader),
            getAll
            );
        return ResponseEntity.ok(watchHistory);
    }

    @PostMapping
    public ResponseEntity<String> addShowToWatchHistory(
        @RequestBody UserWatchHistoryRequest userWatchHistoryRequest,
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.addShowToWatchHistory(
            userWatchHistoryRequest,
            AuthUtil.extractUsernameFromAuthHeader(authHeader)
            );
        return ResponseEntity.ok("Show added to watch history successfully.");
    }

    @PutMapping
    public ResponseEntity<String> updateWatchStatus(
        @RequestBody UserWatchHistoryRequest userWatchHistoryRequest,
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.updateWatchStatus(
            userWatchHistoryRequest,
            AuthUtil.extractUsernameFromAuthHeader(authHeader)
            );
        return ResponseEntity.ok("Watch status updated successfully.");
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<String> deleteShowFromWatchHistory(
        @PathVariable int showId,
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.deleteShowFromWatchHistory(
            AuthUtil.extractUsernameFromAuthHeader(authHeader),
            showId
            );
        return ResponseEntity.ok("Show removed from watch history successfully.");
    }
}
