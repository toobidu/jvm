package com.jvm.mastery.module0.lab01.dynamicstrategy;

public interface SmartPaymentStrategy {
    boolean isApplicable(Order order);
    RoutingResult process(Order order);
    int getPriority();
    String getName();
}
