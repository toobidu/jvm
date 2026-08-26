package com.jvm.mastery.module0.lab02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete Decorator 1: Bổ sung tính năng Logging.
 * Bọc bất kỳ Notifier nào để ghi log trước và sau khi gửi.
 */
public class LoggingNotifierDecorator extends BaseNotifierDecorator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String recipient, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.printf("  📝 [LOG START %s] Kênh: %s | Người nhận: %s%n",
                timestamp, getChannelName(), recipient);

        long startTime = System.currentTimeMillis();

        // Gọi method của đối tượng được bọc bên trong
        super.send(recipient, message);

        long duration = System.currentTimeMillis() - startTime;
        System.out.printf("  📝 [LOG END   %s] Gửi thành công trong %d ms%n",
                timestamp, duration);
    }
}
