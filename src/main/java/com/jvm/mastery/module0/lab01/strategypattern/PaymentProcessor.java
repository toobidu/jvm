package com.jvm.mastery.module0.lab01.strategypattern;

@FunctionalInterface
public interface PaymentProcessor {
    boolean process(PaymentInfo paymentInfo);
}
