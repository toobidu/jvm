package com.jvm.mastery.module0.lab01.strategypattern;

public class GatewayProcessor implements PaymentProcessor {
    private final String gatewayName;

    public GatewayProcessor(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    @Override
    public boolean process(PaymentInfo info) {
        System.out.println("  Gửi yêu cầu tới " + gatewayName + "...");
        return true;
    }
}
