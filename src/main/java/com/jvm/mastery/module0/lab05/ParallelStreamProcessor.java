package com.jvm.mastery.module0.lab05;

import java.util.List;

/**
 * Cách 3: Xử lý Đa luồng bằng Parallel Stream.
 * Cơ chế: Sử dụng ForkJoinPool.commonPool() để chia nhỏ mảng và xử lý trên nhiều Core CPU.
 * Lưu ý: Tốn chi phí chia tách (Splitting) và hợp nhất kết quả (Merging/Join).
 */
public class ParallelStreamProcessor {

    public static double sumSuccessfulECommerce(List<Transaction> transactions) {
        return transactions.parallelStream()
                .filter(Transaction::isSuccessful)
                .filter(t -> "E-COMMERCE".equals(t.category()))
                .mapToDouble(Transaction::amount)
                .sum();
    }
}
