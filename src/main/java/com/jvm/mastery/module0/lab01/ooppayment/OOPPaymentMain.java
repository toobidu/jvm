package com.jvm.mastery.module0.lab01.ooppayment;

import java.util.List;

public class OOPPaymentMain {

    static void processAllPayments(List<Payment> payments) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   XỬ LÝ HÀNG LOẠT THANH TOÁN        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        double totalProcessed = 0;
        int success = 0;
        int failed = 0;

        for (Payment payment : payments) {
            // POLYMORPHISM: gọi execute() nhưng từng loại chạy logic riêng
            PaymentResult result = payment.execute();
            if (result.success()) {
                totalProcessed += result.totalAmount();
                success++;
            } else {
                failed++;
            }
            System.out.println();
        }

        System.out.println("══════════════════════════════════════");
        System.out.println("📊 Tổng kết:");
        System.out.println("  ✅ Thành công: " + success);
        System.out.println("  ❌ Thất bại:   " + failed);
        System.out.printf("  💰 Tổng tiền:  %.2f%n", totalProcessed);
        System.out.println("══════════════════════════════════════");

        System.out.println("\n📋 Receipts:");
        for (Payment payment : payments) {
            System.out.println("  " + payment.getReceipt());
        }
    }

    public static void main(String[] args) {
        var payments = List.of(
            new CreditCardPayment(1_500_000, "VND", "4111111111111234", "123", "12/27"),
            new BankTransferPayment(5_000_000, "VND", "19036214578", "VCB"),
            new EWalletPayment(200_000, "VND", "0912345678", "MoMo"),
            new CreditCardPayment(50, "USD", "1234", "12", "01/25")  // Thẻ Invalid
        );

        processAllPayments(payments);
    }
}
