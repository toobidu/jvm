package com.jvm.mastery.module0.lab05;

public class Lab05Main {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 0 — LAB 0.5: STREAM API VS FOR-LOOP VS PARALLEL STREAM BENCHMARK║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        System.out.println("⏳ Đang Warm-up JVM JIT Compiler...");
        StreamPerformanceBenchmark.warmUp();
        System.out.println("✅ Warm-up hoàn tất!\n");

        // Kịch bản 1: Danh sách nhỏ (N = 100) - Tương đương 90% kết quả REST API trong Spring Boot
        StreamPerformanceBenchmark.runBenchmark(100, 50_000);
        System.out.println();

        // Kịch bản 2: Danh sách vừa (N = 10,000) - Xử lý Batch Job vừa
        StreamPerformanceBenchmark.runBenchmark(10_000, 500);
        System.out.println();

        // Kịch bản 3: Danh sách lớn (N = 1,000,000) - Xử lý dữ liệu lớn / Big Data Export
        StreamPerformanceBenchmark.runBenchmark(1_000_000, 10);
        System.out.println();

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Hoàn thành toàn bộ đo đạc Lab 0.5!");
    }
}
