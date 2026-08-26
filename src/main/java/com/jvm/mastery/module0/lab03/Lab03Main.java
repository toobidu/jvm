package com.jvm.mastery.module0.lab03;

public class Lab03Main {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 0 — LAB 0.3: JAVA COLLECTIONS DEEP-DIVE BENCHMARK    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        int n = 100_000; // 100,000 phần tử

        // 1. Chạy List Benchmark
        ListBenchmark.runBenchmark(n);

        // 2. Chạy Map Benchmark
        MapBenchmark.runBenchmark(n);

        // 3. Chạy Set Benchmark
        SetBenchmark.runBenchmark(n);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Hoàn thành đo đạc Benchmark!");
    }
}
