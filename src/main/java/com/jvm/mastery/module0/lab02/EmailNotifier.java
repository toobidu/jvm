package com.jvm.mastery.module0.lab02;

/**
 * Concrete Component 1: Gửi thông báo qua Email thuần túy.
 * Chỉ tập trung vào nghiệp vụ cốt lõi: Gửi Email.
 */
public class EmailNotifier implements Notifier {

    @Override
    public void send(String recipient, String message) {
        System.out.printf("  📧 [Email sent] To: %s | Content: \"%s\"%n", recipient, message);
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}
