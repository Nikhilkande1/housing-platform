package com.tejasnirman.api.controller;

import com.tejasnirman.api.dto.ApiResponse;
import com.tejasnirman.api.dto.BookingDTO;
import com.tejasnirman.api.security.UserPrincipal;
import com.tejasnirman.api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createBooking(@AuthenticationPrincipal UserPrincipal currentUser, @RequestBody BookingDTO dto) {
        try {
            BookingDTO saved = bookingService.createBooking(currentUser.getId(), dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_OWNER')")
    public ResponseEntity<List<BookingDTO>> getMyBookings(@AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(bookingService.getMyBookings(currentUser.getId()));
    }

    @GetMapping("/property/{propertyId}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<List<BookingDTO>> getPropertyBookings(@PathVariable Long propertyId) {
        return ResponseEntity.ok(bookingService.getPropertyBookings(propertyId));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            BookingDTO updated = bookingService.updateBookingStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
