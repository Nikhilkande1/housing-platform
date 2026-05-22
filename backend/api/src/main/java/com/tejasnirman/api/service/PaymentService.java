package com.tejasnirman.api.service;

import com.tejasnirman.api.dto.PaymentDTO;
import com.tejasnirman.api.model.*;
import com.tejasnirman.api.repository.PaymentRepository;
import com.tejasnirman.api.repository.PropertyRepository;
import com.tejasnirman.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          UserRepository userRepository,
                          PropertyRepository propertyRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    public PaymentDTO createPayment(Long userId, PaymentDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(dto.getAmount());
        payment.setGateway(dto.getGateway() != null ? dto.getGateway() : "RAZORPAY");
        payment.setPaymentStatus(PaymentStatus.SUCCESS); // Auto-complete for mock checkout
        
        // Generate a random transaction ID
        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        if (dto.getPropertyId() != null) {
            Property property = propertyRepository.findById(dto.getPropertyId()).orElse(null);
            payment.setProperty(property);
        }

        Payment saved = paymentRepository.save(payment);
        return convertToDTO(saved);
    }

    public List<PaymentDTO> getMyPayments(Long userId) {
        return paymentRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setGateway(payment.getGateway());
        dto.setTransactionId(payment.getTransactionId());
        dto.setPaymentStatus(payment.getPaymentStatus().name());
        dto.setCreatedAt(payment.getCreatedAt());

        if (payment.getProperty() != null) {
            dto.setPropertyId(payment.getProperty().getId());
            dto.setPropertyTitle(payment.getProperty().getTitle());
        }

        return dto;
    }
}
