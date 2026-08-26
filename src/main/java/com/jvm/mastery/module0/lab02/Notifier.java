package com.jvm.mastery.module0.lab02;

/**
 * Interface chung định nghĩa hành vi gửi thông báo.
 * Mọi Notifier gốc và Decorator đều phải implement interface này.
 */
public interface Notifier {
    void send(String recipient, String message);
    String getChannelName();
}
