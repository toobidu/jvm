package com.jvm.mastery.module0.lab05;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SeniorBenchmarkComparison {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   GIẢI MÃ BÀI TOÁN BENCHMARK 200,000 PHẦN TỬ CỦA SENIOR                 ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        int n = 200_000;
        int iterations = 1_000;

        // Chuẩn bị mảng số nguyên thủy int[] 200k phần tử
        int[] primitiveArray = new int[n];
        for (int i = 0; i < n; i++) primitiveArray[i] = i;

        // ⏳ WARM-UP KỸ CÀNG ĐỂ JIT COMPILER TỐI ƯU CẢ 4 CÁCH CÔNG BẰNG
        System.out.println("⏳ Đang Warm-up JIT Compiler 500 vòng...");
        for (int i = 0; i < 500; i++) {
            runPrimitiveForLoop(primitiveArray);
            runIntStreamSequential(primitiveArray);
            runIntStreamParallel(primitiveArray);
        }
        System.out.println("✅ Warm-up hoàn tất! Bắt đầu đo đạc công bằng:\n");

        // 1. For-loop trên mảng nguyên thủy (Cổ điển)
        long start = System.nanoTime();
        long forSum = 0;
        for (int i = 0; i < iterations; i++) {
            forSum = runPrimitiveForLoop(primitiveArray);
        }
        long forTime = System.nanoTime() - start;

        // 2. Sequential IntStream (Stream tuần tự)
        start = System.nanoTime();
        long seqStreamSum = 0;
        for (int i = 0; i < iterations; i++) {
            seqStreamSum = runIntStreamSequential(primitiveArray);
        }
        long seqStreamTime = System.nanoTime() - start;

        // 3. Parallel IntStream (Stream đa luồng tận dụng toàn bộ Core CPU)
        start = System.nanoTime();
        long parStreamSum = 0;
        for (int i = 0; i < iterations; i++) {
            parStreamSum = runIntStreamParallel(primitiveArray);
        }
        long parStreamTime = System.nanoTime() - start;

        // In kết quả
        System.out.printf("👉 1. Mảng nguyên thủy For-Loop:           %8.2f ms%n", forTime / 1e6);
        System.out.printf("👉 2. Mảng nguyên thủy Sequential IntStream: %8.2f ms%n", seqStreamTime / 1e6);
        System.out.printf("👉 3. Mảng nguyên thủy Parallel IntStream:   %8.2f ms (Đa luồng)%n", parStreamTime / 1e6);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        if (parStreamTime < forTime) {
            System.out.printf("🏆 GIẢI MÃ THÀNH CÔNG: Ở 200k phần tử, PARALLEL STREAM NHANH HƠN %.1fx!%n", (double) forTime / parStreamTime);
            System.out.println("   -> Người Senior của bạn chính xác đã sử dụng Parallel Stream (hoặc IntStream có SIMD Vectorization)!");
        } else {
            System.out.println("🏆 For-loop mảng nguyên thủy và Stream đạt tốc độ tương đương nhau nhờ JIT Vectorization!");
        }
    }

    private static long runPrimitiveForLoop(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private static long runIntStreamSequential(int[] arr) {
        return Arrays.stream(arr).asLongStream().sum();
    }

    private static long runIntStreamParallel(int[] arr) {
        return Arrays.stream(arr).parallel().asLongStream().sum();
    }
}
