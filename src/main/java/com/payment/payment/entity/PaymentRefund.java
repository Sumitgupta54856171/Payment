package com.payment.payment.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_refund")
@Data
public class PaymentRefund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    // Link to the original payment transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private payment transaction;

    @Column(nullable = false)
    private BigDecimal refundAmount;

    // Refund reference ID from the Payment Gateway
    @Column(unique = true)
    private String refundGatewayRefId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status; // Usually: REFUND_REQUESTED, REFUNDED, FAILED

    @Column(length = 255)
    private String reason;

    private LocalDateTime initiatedAt = LocalDateTime.now();

    private LocalDateTime completedAt;
}