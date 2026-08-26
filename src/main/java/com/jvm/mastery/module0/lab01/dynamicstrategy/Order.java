package com.jvm.mastery.module0.lab01.dynamicstrategy;

public record Order(
    String orderId,
    double amount,
    String currency,
    boolean isInternational,
    boolean isVipCustomer
) {}
