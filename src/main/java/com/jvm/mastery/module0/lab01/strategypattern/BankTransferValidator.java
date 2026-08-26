package com.jvm.mastery.module0.lab01.strategypattern;

public class BankTransferValidator implements PaymentValidator {
    @Override
    public boolean validate(PaymentInfo info) {
        System.out.println("  Kiểm tra tài khoản: " + info.bankCode() + " - " + info.accountNumber());
        return info.accountNumber().length() >= 8 && !info.bankCode().isEmpty();
    }
}
