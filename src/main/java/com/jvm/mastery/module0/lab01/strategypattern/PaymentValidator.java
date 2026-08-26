package com.jvm.mastery.module0.lab01.strategypattern;

@FunctionalInterface
public interface PaymentValidator {
    boolean validate(PaymentInfo paymentInfo);
}
