package com.jvm.mastery.module2.lab21;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Lab21Main {

    public static void main(String[] args) throws RunnerException {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 2 — LAB 2.1: JAVA MICROBENCHMARK HARNESS (JMH) ĐÚNG CÁCH       ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        // 1. Minh họa bẫy Dead Code bằng System.nanoTime()
        DeadCodeTrapDemonstration.runNaiveBenchmark();

        // 2. Chạy bộ đo lường chuyên nghiệp chuẩn quốc tế của OpenJDK (JMH)
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🚀 ĐANG KHỞI CHẠY JMH BENCHMARK HARNESS TRÊN MÁY TÍNH CỦA BẠN...");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        Options opt = new OptionsBuilder()
                .include(JmhMathBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Hoàn thành đo đạc Lab 2.1!");
    }
}
