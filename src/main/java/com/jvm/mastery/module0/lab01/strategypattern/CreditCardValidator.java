package com.jvm.mastery.module0.lab01.strategypattern;

public class CreditCardValidator implements PaymentValidator {
    @Override
    public boolean validate(PaymentInfo info) {
        System.out.println("  Kiểm tra thẻ: ****" +
            info.cardNumber().substring(Math.max(0, info.cardNumber().length() - 4)));
        return info.cardNumber().length() == 16 && info.cvv().length() == 3;
    }
}
