package com.jvm.mastery.module0.lab01.dynamicstrategy;

import java.util.List;

public class DynamicStrategyMain {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   DYNAMIC / CONDITIONAL STRATEGY ROUTING DEMO         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        var router = new SmartPaymentRouter(List.of(
            new HighValueEscrowStrategy(),
            new InternationalStripeStrategy(),
            new MicroPaymentMomoStrategy(),
            new DefaultVnPayStrategy()
        ));

        // Case 1: Đơn nhỏ trong nước (150,000 VND) -> MoMo
        var order1 = new Order("ORD_001", 150_000, "VND", false, false);
        router.routeAndPay(order1);

        System.out.println();

        // Case 2: Đơn quốc tế (120 USD) -> Stripe
        var order2 = new Order("ORD_002", 120, "USD", true, false);
        router.routeAndPay(order2);

        System.out.println();

        // Case 3: Đơn giá trị khủng (120,000,000 VND) -> Escrow
        var order3 = new Order("ORD_003", 120_000_000, "VND", false, true);
        router.routeAndPay(order3);

        System.out.println();

        // Case 4: Đơn vừa tiêu chuẩn (15,000,000 VND) -> VNPay Standard
        var order4 = new Order("ORD_004", 15_000_000, "VND", false, false);
        router.routeAndPay(order4);

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
