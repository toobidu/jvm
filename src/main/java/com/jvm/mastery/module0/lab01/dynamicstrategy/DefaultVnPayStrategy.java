package com.jvm.mastery.module0.lab01.dynamicstrategy;

public class DefaultVnPayStrategy implements SmartPaymentStrategy {

    @Override
    public boolean isApplicable(Order order) {
        return "VND".equalsIgnoreCase(order.currency()) && !order.isInternational();
    }

    @Override
    public RoutingResult process(Order order) {
        System.out.printf("  💳 [VNPay] Thanh toán tiêu chuẩn qua Cổng VNPay (%,.0f VND)%n", order.amount());
        return new RoutingResult(getName(), "Chuyển hướng cổng VNPay thành công", order.amount() * 0.02);
    }

    @Override
    public int getPriority() {
        return 0; // Fallback mặc định
    }

    @Override
    public String getName() {
        return "VNPay Standard Gateway";
    }
}
