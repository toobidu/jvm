package com.jvm.mastery.module0.lab01.ooppayment;

public class EWalletPayment extends Payment {

    private final String phoneNumber;
    private final String walletProvider;

    public EWalletPayment(double amount, String currency, String phoneNumber, String walletProvider) {
        super(amount, currency);
        this.phoneNumber = phoneNumber;
        this.walletProvider = walletProvider;
    }

    @Override
    protected boolean validate() {
        System.out.println("  Xác thực ví " + walletProvider + ": " + phoneNumber);
        return phoneNumber.startsWith("0") && phoneNumber.length() == 10;
    }

    @Override
    protected boolean processPayment() {
        System.out.println("  Gửi yêu cầu tới " + walletProvider + " API...");
        return true;
    }

    @Override
    public double calculateFee() {
        return getAmount() * 0.01; // 1%
    }

    @Override
    public String getPaymentType() {
        return "E-Wallet (" + walletProvider + ")";
    }
}
