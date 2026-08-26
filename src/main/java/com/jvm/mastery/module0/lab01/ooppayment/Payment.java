package com.jvm.mastery.module0.lab01.ooppayment;

/**
 * Payment là abstract class vì:
 * - Có state chung (amount, currency, status) -> cần field
 * - Có behavior chung (getReceipt()) -> cần method concrete
 * - Có behavior khác nhau tùy loại (validate, process, calculateFee) -> abstract method
 * - Áp dụng Template Method Pattern ở method execute()
 */
public abstract class Payment {

    private final double amount;
    private final String currency;
    private PaymentStatus status;

    protected Payment(double amount, String currency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount phải > 0, nhận được: " + amount);
        }
        this.amount = amount;
        this.currency = currency;
        this.status = PaymentStatus.PENDING;
    }

    protected abstract boolean validate();
    protected abstract boolean processPayment();
    public abstract double calculateFee();
    public abstract String getPaymentType();

    // Template Method Pattern
    public final PaymentResult execute() {
        System.out.println("━━━ Xử lý " + getPaymentType() + " ━━━");
        System.out.println("  Số tiền: " + amount + " " + currency);

        if (!validate()) {
            this.status = PaymentStatus.FAILED;
            System.out.println("  ❌ Xác thực thất bại!");
            return new PaymentResult(false, "Validation failed", 0);
        }
        System.out.println("  ✅ Xác thực thành công");

        if (!processPayment()) {
            this.status = PaymentStatus.FAILED;
            System.out.println("  ❌ Xử lý thất bại!");
            return new PaymentResult(false, "Processing failed", 0);
        }

        double fee = calculateFee();
        double total = amount + fee;
        this.status = PaymentStatus.COMPLETED;

        System.out.println("  💰 Phí giao dịch: " + fee + " " + currency);
        System.out.println("  💳 Tổng thanh toán: " + total + " " + currency);
        System.out.println("  ✅ Thanh toán thành công!");

        return new PaymentResult(true, "Success", total);
    }

    public String getReceipt() {
        return String.format("[%s] %s: %.2f %s — Trạng thái: %s",
                getPaymentType(), status, amount, currency, status);
    }

    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public PaymentStatus getStatus() { return status; }
}
