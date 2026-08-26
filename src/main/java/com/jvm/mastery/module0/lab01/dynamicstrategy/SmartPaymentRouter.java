package com.jvm.mastery.module0.lab01.dynamicstrategy;

import java.util.Comparator;
import java.util.List;

public class SmartPaymentRouter {

    private final List<SmartPaymentStrategy> strategies;

    public SmartPaymentRouter(List<SmartPaymentStrategy> strategies) {
        this.strategies = strategies.stream()
                .sorted(Comparator.comparingInt(SmartPaymentStrategy::getPriority).reversed())
                .toList();
    }

    public RoutingResult routeAndPay(Order order) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📦 Đơn hàng: %s | Tiền: %,.0f %s | Quốc tế: %s | VIP: %s%n",
                order.orderId(), order.amount(), order.currency(), order.isInternational(), order.isVipCustomer());

        SmartPaymentStrategy selectedStrategy = strategies.stream()
                .filter(strategy -> strategy.isApplicable(order))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy cổng thanh toán phù hợp cho đơn: " + order.orderId()));

        System.out.println("👉 [Router] Tự động chọn kênh: " + selectedStrategy.getName() + " (Priority: " + selectedStrategy.getPriority() + ")");

        return selectedStrategy.process(order);
    }
}
