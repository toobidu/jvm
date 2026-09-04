package com.jvm.mastery.module1.lab12;

public class Lab12Main {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 1 — LAB 1.2: PRIMITIVE VS WRAPPER — CHI PHÍ ẨN CỦA OBJECT      ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        // 1. Đo đạc đơn lẻ: 1 int vs 1 Integer
        PrimitiveVsWrapperMeasurement.measureSingleObjects();

        // 2. Đo đạc quy mô lớn: 1,000,000 số
        MillionNumbersMemoryComparison.compareMillionElements(1_000_000);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Hoàn thành đo đạc Lab 1.2!");
    }
}
