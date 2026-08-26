package com.jvm.mastery.module0.lab02;

/**
 * =====================================================================
 * TODO DÀNH CHO BẠN: RetryNotifierDecorator
 * =====================================================================
 * <p>
 * MỤC TIÊU:
 * Bổ sung tính năng Tự Động Thử Lại (Retry) khi việc gửi thông báo gặp lỗi.
 * <p>
 * YÊU CẦU:
 * 1. Kế thừa từ BaseNotifierDecorator.
 * 2. Có field: private final int maxRetries.
 * 3. Override method send(recipient, message):
 * - Dùng vòng lặp chạy từ 1 đến maxRetries.
 * - Bọc lời gọi super.send(recipient, message) trong khối try-catch.
 * - Nếu thành công -> return ngay (thoát khỏi hàm).
 * - Nếu bắt được Exception:
 * In ra: "  ⚠️ [RETRY #attempt/maxRetries] Gửi thất bại, đang thử lại..."
 * Nếu đã thử hết số lần mà vẫn lỗi -> ném RuntimeException ra ngoài.
 * =====================================================================
 */
public class RetryNotifierDecorator extends BaseNotifierDecorator {

    private final int maxRetries;

    public RetryNotifierDecorator(Notifier wrapped, int maxRetries) {
        super(wrapped);
        this.maxRetries = maxRetries;
    }

    @Override
    public void send(String recipient, String message) {
        // TODO: BẠN HÃY IMPLEMENT LOGIC RETRY TẠI ĐÂY!
        // Gợi ý:
        // for (int attempt = 1; attempt <= maxRetries; attempt++) {
        //     try {
        //         super.send(recipient, message);
        //         return; // Thành công
        //     } catch (Exception e) {
        //         System.out.printf("  ⚠️ [RETRY #%d/%d] Gặp lỗi: %s%n", attempt, maxRetries, e.getMessage());
        //         if (attempt == maxRetries) {
        //             throw new RuntimeException("Đã thử " + maxRetries + " lần nhưng thất bại hoàn toàn!", e);
        //         }
        //     }
        // }
        for (int i = 1; i <= maxRetries; i++) {
            try {
                super.send(recipient, message);
                return;
            } catch (Exception e) {
                System.out.printf("  ⚠️ [RETRY #%d/%d] Gặp lỗi: %s%n", i, maxRetries, e.getMessage());
                if (i == maxRetries)
                    throw new RuntimeException("Đã thử " + maxRetries + " lần nhưng thất bại hoàn toàn!", e);
            }
        }
    }
}
