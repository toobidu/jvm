package com.jvm.mastery.module0.lab01.dynamicstrategy;

public class InternationalStripeStrategy implements SmartPaymentStrategy {

    @Override
    public boolean isApplicable(Order order) {
        return order.isInternational() || !"VND".equalsIgnoreCase(order.currency());
    }

    @Override
    public RoutingResult process(Order order) {
        System.out.printf("  🌍 [Stripe] Xử lý thanh toán quốc tế qua Stripe Gateway (%,.2f %s)%n",
                order.amount(), order.currency());
        return new RoutingResult(getName(), "Thanh toán qua Stripe SDK thành công", order.amount() * 0.035);
    }

    @Override
    public int getPriority() {
        return 80;
    }

    @Override
    public String getName() {
        return "Stripe Global Gateway";
    }
}
