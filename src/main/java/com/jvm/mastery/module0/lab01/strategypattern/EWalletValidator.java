package com.jvm.mastery.module0.lab01.strategypattern;

public class EWalletValidator implements PaymentValidator {
    @Override
    public boolean validate(PaymentInfo info) {
        System.out.println("  Xác thực ví " + info.walletProvider() + ": " + info.phoneNumber());
        return info.phoneNumber().startsWith("0") && info.phoneNumber().length() == 10;
    }
}
