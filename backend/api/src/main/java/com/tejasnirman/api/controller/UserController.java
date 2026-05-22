package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.UserProfileDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getCurrentUserProfile(@AuthenticationPrincipal UserPrincipal currentUser) {
        try {
            UserProfileDTO profile = userService.getUserProfile(currentUser.getId());
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateProfile(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody UserProfileDTO dto) {
        try {
            UserProfileDTO updated = userService.updateProfile(currentUser.getId(), dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
