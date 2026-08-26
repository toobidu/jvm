package com.jvm.mastery.module0.lab01.ooppayment;

public class BankTransferPayment extends Payment {

    private final String accountNumber;
    private final String bankCode;

    public BankTransferPayment(double amount, String currency, String accountNumber, String bankCode) {
        super(amount, currency);
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    @Override
    protected boolean validate() {
        System.out.println("  Kiểm tra tài khoản: " + bankCode + " - " + accountNumber);
        return accountNumber.length() >= 8 && !bankCode.isEmpty();
    }

    @Override
    protected boolean processPayment() {
        System.out.println("  Gửi lệnh chuyển khoản qua NAPAS...");
        return true;
    }

    @Override
    public double calculateFee() {
        return "VND".equals(getCurrency()) ? 10_000 : 0.5;
    }

    @Override
    public String getPaymentType() {
        return "Bank Transfer";
    }
}
