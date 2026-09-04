package com.jvm.mastery.module1.lab13;

public class Lab13Main {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 1 — LAB 1.3: GIẢI PHẪU CẤU TRÚC NỘI BỘ HASHMAP BẰNG JOL        ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        // 1. Giải phẫu cấu trúc cây đối tượng HashMap
        HashMapDeepAnatomyMeasurement.inspectHashMapStructure();

        // 2. Khảo sát hiện tượng Resize & Rehashing
        HashMapResizeOverheadBenchmark.compareResizeVsInitialCapacity(100_000);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Hoàn thành đo đạc Lab 1.3!");
    }
}
