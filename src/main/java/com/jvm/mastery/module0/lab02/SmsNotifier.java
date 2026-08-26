package com.jvm.mastery.module0.lab02;

/**
 * Concrete Component 2: Gửi thông báo qua SMS thuần túy.
 */
public class SmsNotifier implements Notifier {

    @Override
    public void send(String recipient, String message) {
        System.out.printf("  📱 [SMS sent] Phone: %s | Content: \"%s\"%n", recipient, message);
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
