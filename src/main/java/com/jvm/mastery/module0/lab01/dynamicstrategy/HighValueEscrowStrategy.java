package com.jvm.mastery.module0.lab01.dynamicstrategy;

public class HighValueEscrowStrategy implements SmartPaymentStrategy {

    @Override
    public boolean isApplicable(Order order) {
        return "VND".equalsIgnoreCase(order.currency()) && order.amount() >= 50_000_000;
    }

    @Override
    public RoutingResult process(Order order) {
        System.out.printf("  🔒 [Escrow] Chuyển đơn giá trị lớn (%,.0f VND) qua bảo lãnh ngân hàng VCB%n", order.amount());
        return new RoutingResult(getName(), "Tạo hợp đồng bảo lãnh thành công", 20_000);
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public String getName() {
        return "High Value Bank Escrow";
    }
}
