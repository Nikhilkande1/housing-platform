package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.BookingDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.BookingRepository;
import com.tejasnirman.api.repository.PropertyRepository;
import com.tejasnirman.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          PropertyRepository propertyRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public BookingDTO createBooking(Long userId, BookingDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + dto.getPropertyId()));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setProperty(property);
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setStatus(BookingStatus.PENDING);

        // Calculate total amount based on: days * (monthlyRent / 30)
        long days = ChronoUnit.DAYS.between(dto.getCheckIn(), dto.getCheckOut());
        if (days <= 0) {
            days = 1; // Minimum 1 day
        }

        BigDecimal rent = property.getMonthlyRent() != null ? property.getMonthlyRent() : BigDecimal.ZERO;
        BigDecimal dailyRate = rent.divide(new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalAmount = dailyRate.multiply(new BigDecimal(days));
        booking.setTotalAmount(totalAmount);

        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public List<BookingDTO> getMyBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getPropertyBookings(Long propertyId) {
        return bookingRepository.findByPropertyId(propertyId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setPropertyId(booking.getProperty().getId());
        dto.setCheckIn(booking.getCheckIn());
        dto.setCheckOut(booking.getCheckOut());
        dto.setUserName(booking.getUser().getName());
        dto.setPropertyTitle(booking.getProperty().getTitle());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setStatus(booking.getStatus().name());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }
}
