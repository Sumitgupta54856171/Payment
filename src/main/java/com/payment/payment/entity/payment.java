package com.payment.payment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transaction")
@Data // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class payment {

    // Primary Key (Using UUID as String for Microservice-friendly IDs)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    // Foreign Key to the Order Service's Order
    @Column(nullable = false, updatable = false)
    private Long orderId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(length = 3)
    private String currency; // E.g., INR

    // External ID from the Payment Gateway (Crucial for reconciliation)
    @Column(unique = true)
    private String paymentGatewayRefId;

    // Payment Status using the Enum
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(length = 50)
    private String paymentMethod; // E.g., CARD, UPI

    // Token for recurring payments (highly secured)
    @Column(length = 255)
    private String cardToken;

    private BigDecimal feeAmount;

    // Auditing Fields (Auto-managed)
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
