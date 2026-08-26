package com.jvm.mastery.module0.lab01.dynamicstrategy;

public class MicroPaymentMomoStrategy implements SmartPaymentStrategy {

    @Override
    public boolean isApplicable(Order order) {
        return "VND".equalsIgnoreCase(order.currency()) && !order.isInternational() && order.amount() < 2_000_000;
    }

    @Override
    public RoutingResult process(Order order) {
        System.out.printf("  📱 [MoMo] Thanh toán đơn hàng nhỏ (%,.0f VND) qua ví MoMo QR%n", order.amount());
        return new RoutingResult(getName(), "Tạo QR MoMo thành công", order.amount() * 0.01);
    }

    @Override
    public int getPriority() {
        return 60;
    }

    @Override
    public String getName() {
        return "MoMo E-Wallet";
    }
}
