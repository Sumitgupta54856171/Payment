package com.payment.payment.entity;

public enum PaymentStatus {
    INITIATED,      // Payment process started
    PENDING,        // Waiting for confirmation (e.g., from bank)
    SUCCESS,        // Payment successful
    FAILED,         // Payment failed
    REFUND_REQUESTED, // Refund process started
    REFUNDED,       // Refund completed
    CANCELLED       // Transaction cancelled before processing
}