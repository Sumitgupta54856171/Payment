package com.payment.payment.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_log")
@Data
public class PaymentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    // Link to the main transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private payment transaction;

    @Column(nullable = false, length = 20)
    private String oldStatus;

    @Column(nullable = false, length = 20)
    private String newStatus;

    @Column(length = 50)
    private String eventSource; // E.g., GATEWAY_WEBHOOK, ORDER_SERVICE

    // Storing the full response/details from the gateway (e.g., JSON string)
    @Column(columnDefinition = "TEXT")
    private String eventDetails;

    @Column(updatable = false)
    private LocalDateTime loggedAt = LocalDateTime.now();
}