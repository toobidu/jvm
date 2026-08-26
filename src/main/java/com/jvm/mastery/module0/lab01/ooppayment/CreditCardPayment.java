package com.jvm.mastery.module0.lab01.ooppayment;

public class CreditCardPayment extends Payment {

    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(double amount, String currency, String cardNumber, String cvv, String expiryDate) {
        super(amount, currency);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    protected boolean validate() {
        System.out.println("  Kiểm tra thẻ: ****" + cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
        return cardNumber.length() == 16 && cvv.length() == 3;
    }

    @Override
    protected boolean processPayment() {
        System.out.println("  Gửi yêu cầu tới Payment Gateway...");
        return true;
    }

    @Override
    public double calculateFee() {
        return getAmount() * 0.025; // 2.5%
    }

    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}
