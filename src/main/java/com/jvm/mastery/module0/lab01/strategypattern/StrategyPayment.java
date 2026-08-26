package com.jvm.mastery.module0.lab01.strategypattern;

public class StrategyPayment {

    private final PaymentInfo info;
    private final PaymentValidator validator;
    private final FeeCalculator feeCalculator;
    private final PaymentProcessor processor;
    private String status = "PENDING";

    public StrategyPayment(PaymentInfo info,
                           PaymentValidator validator,
                           FeeCalculator feeCalculator,
                           PaymentProcessor processor) {
        this.info = info;
        this.validator = validator;
        this.feeCalculator = feeCalculator;
        this.processor = processor;
    }

    public PaymentResult execute() {
        System.out.println("━━━ Xử lý " + info.paymentType() + " ━━━");
        System.out.println("  Số tiền: " + info.amount() + " " + info.currency());

        // Gọi Strategy Validator
        if (!validator.validate(info)) {
            status = "FAILED";
            System.out.println("  ❌ Xác thực thất bại!");
            return new PaymentResult(false, "Validation failed", 0);
        }
        System.out.println("  ✅ Xác thực thành công");

        // Gọi Strategy Processor
        if (!processor.process(info)) {
            status = "FAILED";
            System.out.println("  ❌ Xử lý thất bại!");
            return new PaymentResult(false, "Processing failed", 0);
        }

        // Gọi Strategy FeeCalculator
        double fee = feeCalculator.calculate(info.amount(), info.currency());
        double total = info.amount() + fee;
        status = "COMPLETED";

        System.out.println("  💰 Phí giao dịch: " + fee + " " + info.currency());
        System.out.println("  💳 Tổng thanh toán: " + total + " " + info.currency());
        System.out.println("  ✅ Thanh toán thành công!");

        return new PaymentResult(true, "Success", total);
    }

    public String getReceipt() {
        return String.format("[%s] %s: %.2f %s — Trạng thái: %s",
                info.paymentType(), status, info.amount(), info.currency(), status);
    }
}
