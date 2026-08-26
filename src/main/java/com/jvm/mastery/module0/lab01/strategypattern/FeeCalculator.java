package com.jvm.mastery.module0.lab01.strategypattern;

@FunctionalInterface
public interface FeeCalculator {
    double calculate(double amount, String currency);
}
