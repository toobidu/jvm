package com.jvm.mastery.module0.lab01.strategypattern;

import java.util.List;

public class StrategyPatternMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  STRATEGY PATTERN — Payment System       ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // 1. Khởi tạo các Strategies
        var creditCardValidator = new CreditCardValidator();
        var bankValidator = new BankTransferValidator();
        var eWalletValidator = new EWalletValidator();

        var creditCardFee = new PercentageFeeCalculator(0.025);  // 2.5%
        var eWalletFee = new PercentageFeeCalculator(0.01);       // 1%
        var bankFee = new FixedFeeCalculator(10_000, 0.5);

        var gateway = new GatewayProcessor("Payment Gateway");
        var napas = new GatewayProcessor("NAPAS");
        var momoApi = new GatewayProcessor("MoMo API");

        // 2. Tạo danh sách Payments bằng cách tổ hợp các Strategies
        var payments = List.of(
            new StrategyPayment(
                PaymentInfo.creditCard(1_500_000, "VND", "4111111111111234", "123", "12/27"),
                creditCardValidator, creditCardFee, gateway
            ),
            new StrategyPayment(
                PaymentInfo.bankTransfer(5_000_000, "VND", "19036214578", "VCB"),
                bankValidator, bankFee, napas
            ),
            new StrategyPayment(
                PaymentInfo.eWallet(200_000, "VND", "0912345678", "MoMo"),
                eWalletValidator, eWalletFee, momoApi
            ),
            new StrategyPayment(
                PaymentInfo.creditCard(50, "USD", "1234", "12", "01/25"),
                creditCardValidator, creditCardFee, gateway
            )
        );

        // 3. Thực thi
        double totalProcessed = 0;
        int success = 0, failed = 0;

        for (var payment : payments) {
            var result = payment.execute();
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

        // Bonus: Thay đổi Strategy tại Runtime bằng Lambda!
        System.out.println("\n═══ 🎁 BONUS: Tổ hợp mới tại runtime với Lambda ═══\n");

        var freePromo = new StrategyPayment(
            PaymentInfo.eWallet(500_000, "VND", "0987654321", "ZaloPay"),
            eWalletValidator,
            (amount, currency) -> 0.0,   // Lambda Strategy miễn phí!
            new GatewayProcessor("ZaloPay API")
        );
        System.out.println("▶ Campaign miễn phí phí giao dịch:");
        freePromo.execute();
    }
}
