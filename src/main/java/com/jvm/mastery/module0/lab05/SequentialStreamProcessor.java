package com.jvm.mastery.module0.lab05;

import java.util.List;

/**
 * Cách 2: Xử lý theo phong cách Khai báo hàm (Functional Stream API).
 * Ưu điểm: Code ngắn gọn, dễ đọc, dễ bảo trì.
 * Nhược điểm: Phải cấp phát các đối tượng Pipeline, Spliterator, Sink chain trên RAM.
 */
public class SequentialStreamProcessor {

    public static double sumSuccessfulECommerce(List<Transaction> transactions) {
        return transactions.stream()
                .filter(Transaction::isSuccessful)
                .filter(t -> "E-COMMERCE".equals(t.category()))
                .mapToDouble(Transaction::amount)
                .sum();
    }
}
