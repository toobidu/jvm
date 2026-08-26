package com.jvm.mastery.module0.lab05;

import java.util.List;

/**
 * Cách 1: Xử lý theo phong cách Mệnh lệnh cổ điển (Imperative For-Loop).
 * Ưu điểm:
 * - Không sinh ra thêm bất kỳ Object trung gian nào trên Heap.
 * - CPU chạy trực tiếp bằng các thanh ghi Register và lệnh nhảy (jump/goto) ở tầng mã máy.
 */
public class ForLoopProcessor {

    public static double sumSuccessfulECommerce(List<Transaction> transactions) {
        double total = 0.0;
        int size = transactions.size();
        for (int i = 0; i < size; i++) {
            Transaction t = transactions.get(i);
            if (t.isSuccessful() && "E-COMMERCE".equals(t.category())) {
                total += t.amount();
            }
        }
        return total;
    }
}
