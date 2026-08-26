package com.jvm.mastery.module0.lab01.strategypattern;

public record PaymentInfo(
    double amount,
    String currency,
    String paymentType,
    String cardNumber,
    String cvv,
    String expiryDate,
    String accountNumber,
    String bankCode,
    String phoneNumber,
    String walletProvider
) {
    public static PaymentInfo creditCard(double amount, String currency,
                                         String cardNumber, String cvv, String expiryDate) {
        return new PaymentInfo(amount, currency, "Credit Card",
                cardNumber, cvv, expiryDate, null, null, null, null);
    }

    public static PaymentInfo bankTransfer(double amount, String currency,
                                           String accountNumber, String bankCode) {
        return new PaymentInfo(amount, currency, "Bank Transfer",
                null, null, null, accountNumber, bankCode, null, null);
    }

    public static PaymentInfo eWallet(double amount, String currency,
                                      String phoneNumber, String walletProvider) {
        return new PaymentInfo(amount, currency, "E-Wallet (" + walletProvider + ")",
                null, null, null, null, null, phoneNumber, walletProvider);
    }
}
