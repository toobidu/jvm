package com.jvm.mastery.module0.lab02;

/**
 * =====================================================================
 * LAB 0.2: MAIN DEMO DECORATOR PATTERN
 * =====================================================================
 *
 * Xem cách các Decorator được bọc lồng nhau (Chain of Decorators):
 * Giống hệt cách Java I/O hoạt động:
 *   new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")))
 *
 * Hay trong Spring Web Filter / Security Filter Chain!
 * =====================================================================
 */
public class Lab02Main {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 0 — LAB 0.2: DECORATOR PATTERN DEMO          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // 1. Gửi Email thông thường (Không decorator)
        System.out.println("1️⃣ KÊNH EMAIL THUẦN TÚY:");
        Notifier simpleEmail = new EmailNotifier();
        simpleEmail.send("user@example.com", "Chào mừng bạn đến với hệ thống!");

        System.out.println("\n─────────────────────────────────────────────────────────\n");

        // 2. Gửi Email CÓ GHI LOG (Bọc LoggingDecorator)
        System.out.println("2️⃣ EMAIL + LOGGING:");
        Notifier loggedEmail = new LoggingNotifierDecorator(new EmailNotifier());
        loggedEmail.send("user@example.com", "Mã kích hoạt tài khoản của bạn là 123456");

        System.out.println("\n─────────────────────────────────────────────────────────\n");

        // 3. Gửi SMS vừa CÓ LOG vừa CÓ RETRY (Bọc 2 lớp Decorator!)
        System.out.println("3️⃣ SMS + RETRY + LOGGING (Tổ hợp 2 Decorator):");
        
        // 💡 Chú ý thứ tự bọc:
        // LoggingDecorator( ngoài cùng ) -> RetryDecorator -> SmsNotifier (lõi)
        Notifier multiLayerSms = new LoggingNotifierDecorator(
            new RetryNotifierDecorator(
                new SmsNotifier(), 
                3 // maxRetries = 3
            )
        );

        multiLayerSms.send("0912345678", "OTP của bạn là 9999");

        System.out.println("\n─────────────────────────────────────────────────────────\n");

        // 4. Giả lập một Notifier luôn bị lỗi mạng để test RetryDecorator
        System.out.println("4️⃣ TEST RETRY VỚI KÊNH BỊ LỖI MẠNG:");
        
        // Tạo một Notifier lỗi mạng 2 lần đầu, lần 3 mới thành công
        Notifier unstableNotifier = new UnstableNetworkNotifier();

        Notifier robustNotifier = new LoggingNotifierDecorator(
            new RetryNotifierDecorator(unstableNotifier, 3)
        );

        try {
            robustNotifier.send("admin@company.com", "Cảnh báo server quá tải!");
        } catch (Exception e) {
            System.out.println("  ❌ Đơn gửi thất bại sau khi đã thử hết số lần retry!");
        }
    }
}

/**
 * Class giả lập mạng chập chờn để kiểm chứng tính năng Retry
 */
class UnstableNetworkNotifier implements Notifier {
    private int failCount = 0;

    @Override
    public void send(String recipient, String message) {
        failCount++;
        if (failCount < 3) {
            System.out.println("  💥 [Network Error] Mạng bị ngắt kết nối tạm thời (Lần " + failCount + ")!");
            throw new RuntimeException("Connection Timeout to gateway");
        }
        System.out.printf("  🚀 [Unstable Channel Success] Đã gửi thành công tới: %s ở lần thử thứ %d%n", recipient, failCount);
    }

    @Override
    public String getChannelName() {
        return "Unstable-Network-Channel";
    }
}
