package com.jvm.mastery.module0.lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamPerformanceBenchmark {

    public static List<Transaction> generateData(int count) {
        Random random = new Random(42); // Cố định seed để dữ liệu nhất quán
        String[] categories = {"E-COMMERCE", "GAMING", "FOOD", "TRAVEL", "BILLS"};
        List<Transaction> list = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            list.add(new Transaction(
                i + 1,
                100_000 + random.nextInt(900_000),
                "VND",
                categories[random.nextInt(categories.length)],
                random.nextBoolean()
            ));
        }
        return list;
    }

    public static void runBenchmark(int count, int iterations) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 ĐO ĐẠC HIỆU NĂNG: N = %,d PHẦN TỬ (Chạy lặp %,d lần)%n", count, iterations);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        List<Transaction> data = generateData(count);

        // 1. Đo For-loop
        long start = System.nanoTime();
        double forLoopResult = 0;
        for (int i = 0; i < iterations; i++) {
            forLoopResult = ForLoopProcessor.sumSuccessfulECommerce(data);
        }
        long forLoopTimeNs = System.nanoTime() - start;

        // 2. Đo Sequential Stream
        start = System.nanoTime();
        double seqStreamResult = 0;
        for (int i = 0; i < iterations; i++) {
            seqStreamResult = SequentialStreamProcessor.sumSuccessfulECommerce(data);
        }
        long seqStreamTimeNs = System.nanoTime() - start;

        // 3. Đo Parallel Stream
        start = System.nanoTime();
        double parStreamResult = 0;
        for (int i = 0; i < iterations; i++) {
            parStreamResult = ParallelStreamProcessor.sumSuccessfulECommerce(data);
        }
        long parStreamTimeNs = System.nanoTime() - start;

        // In kết quả
        double forLoopMs = forLoopTimeNs / 1_000_000.0;
        double seqStreamMs = seqStreamTimeNs / 1_000_000.0;
        double parStreamMs = parStreamTimeNs / 1_000_000.0;

        System.out.printf("  1. Cổ điển (For-Loop):        %8.2f ms | Tổng tiền: %,.0f VND%n", forLoopMs, forLoopResult);
        System.out.printf("  2. Tuần tự (Sequential Stream):%8.2f ms | Tổng tiền: %,.0f VND%n", seqStreamMs, seqStreamResult);
        System.out.printf("  3. Đa luồng (Parallel Stream):  %8.2f ms | Tổng tiền: %,.0f VND%n", parStreamMs, parStreamResult);

        // Đánh giá ai nhanh nhất
        if (forLoopMs <= seqStreamMs && forLoopMs <= parStreamMs) {
            System.out.printf("  👉 🏆 KẾT LUẬN: For-loop NHANH NHẤT (Nhanh hơn Sequential Stream %.1fx)%n", seqStreamMs / forLoopMs);
        } else if (parStreamMs < forLoopMs) {
            System.out.printf("  👉 🏆 KẾT LUẬN: Parallel Stream NHANH NHẤT (Nhanh hơn For-loop %.1fx nhờ tận dụng đa Core CPU)%n", forLoopMs / parStreamMs);
        }
    }

    public static void warmUp() {
        List<Transaction> dummy = generateData(10_000);
        for (int i = 0; i < 500; i++) {
            ForLoopProcessor.sumSuccessfulECommerce(dummy);
            SequentialStreamProcessor.sumSuccessfulECommerce(dummy);
            ParallelStreamProcessor.sumSuccessfulECommerce(dummy);
        }
    }
}
