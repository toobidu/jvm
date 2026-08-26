package com.jvm.mastery.module0.lab05;

/**
 * Record đại diện cho 1 Giao dịch tài chính.
 */
public record Transaction(
    long id,
    double amount,
    String currency,
    String category,
    boolean isSuccessful
) {}
