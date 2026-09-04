package com.jvm.mastery.module2.lab21;

/**
 * =====================================================================
 * MINH HỌA BẪY 1: DEAD CODE ELIMINATION (JIT TỰ Ý XÓA CODE CHẾT)
 * =====================================================================
 */
public class DeadCodeTrapDemonstration {

    public static void runNaiveBenchmark() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("⚠️ BẪY MICRO-BENCHMARK: DEAD CODE ELIMINATION TRÊN JVM");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        int count = 10_000_000;

        // 1. Kịch bản có lỗi: Tính toán nhưng KHÔNG sử dụng kết quả (Dead Code)
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            double dummy = Math.sqrt(i) * Math.sin(i); // Kết quả bị vứt đi!
        }
        long deadCodeTimeNs = System.nanoTime() - start;

        // 2. Kịch bản ngăn chặn JIT xóa code: Gom kết quả và in ra
        start = System.nanoTime();
        double realSum = 0;
        for (int i = 0; i < count; i++) {
            realSum += Math.sqrt(i) * Math.sin(i); // Kết quả được gom lại
        }
        long realCodeTimeNs = System.nanoTime() - start;

        System.out.printf("1️⃣ Đo đo lường có lỗi (Dead Code - JIT XÓA SẠCH VÒNG LẶP): %8.3f ms%n", deadCodeTimeNs / 1e6);
        System.out.printf("2️⃣ Đo lường có sử dụng kết quả (Code thực sự được chạy):  %8.3f ms%n", realCodeTimeNs / 1e6);
        System.out.println("   (Tổng kết quả thực: " + realSum + ")\n");

        System.out.println("👉 GIẢI MÃ: Ở kịch bản 1, trình biên dịch JIT phát hiện biến 'dummy' không ai dùng,");
        System.out.println("   nó đã XÓA TOÀN BỘ 10 TRIỆU PHÉP TÍNH ra khỏi mã máy Assembly!");
    }
}
