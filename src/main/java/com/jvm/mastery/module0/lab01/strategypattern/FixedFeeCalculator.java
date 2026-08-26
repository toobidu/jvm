package com.jvm.mastery.module0.lab01.strategypattern;

public class FixedFeeCalculator implements FeeCalculator {
    private final double vndFee;
    private final double otherFee;

    public FixedFeeCalculator(double vndFee, double otherFee) {
        this.vndFee = vndFee;
        this.otherFee = otherFee;
    }

    @Override
    public double calculate(double amount, String currency) {
        return "VND".equals(currency) ? vndFee : otherFee;
    }
}
