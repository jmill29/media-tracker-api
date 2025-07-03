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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.service.UserWatchHistoryService;

@RestController
@RequestMapping("/api/watch-history")
public class UserWatchHistoryController {

    private final UserWatchHistoryService userWatchHistoryService;

    @Autowired
    public UserWatchHistoryController(UserWatchHistoryService userWatchHistoryService) {
        this.userWatchHistoryService = userWatchHistoryService;
    }

    // Define endpoints for user watch history operations here
    @GetMapping("/{username}")
    public ResponseEntity<List<UserWatchHistoryDto>> getWatchHistoryByUsername(@PathVariable String username, @RequestParam(required = false, defaultValue = "false") boolean getAll) {
        List<UserWatchHistoryDto> watchHistory = userWatchHistoryService.getWatchHistoryByUsername(username, getAll);
        return ResponseEntity.ok(watchHistory);
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> addShowToWatchHistory(@RequestBody UserWatchHistoryRequest userWatchHistoryRequest, @PathVariable String username) {
        userWatchHistoryService.addShowToWatchHistory(userWatchHistoryRequest, username);
        return ResponseEntity.ok("Show added to watch history successfully.");
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> updateWatchStatus(@RequestBody UserWatchHistoryRequest userWatchHistoryRequest, @PathVariable String username) {
        userWatchHistoryService.updateWatchStatus(userWatchHistoryRequest, username);
        return ResponseEntity.ok("Watch status updated successfully.");
    }

    @DeleteMapping("/{username}/{showId}")
    public ResponseEntity<String> deleteShowFromWatchHistory(@PathVariable String username, @PathVariable int showId) {
        userWatchHistoryService.deleteShowFromWatchHistory(username, showId);
        return ResponseEntity.ok("Show removed from watch history successfully.");
    }
}
