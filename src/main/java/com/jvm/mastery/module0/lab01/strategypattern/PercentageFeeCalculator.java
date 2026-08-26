package com.jvm.mastery.module0.lab01.strategypattern;

public class PercentageFeeCalculator implements FeeCalculator {
    private final double percentage;

    public PercentageFeeCalculator(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculate(double amount, String currency) {
        return amount * percentage;
    }
}
