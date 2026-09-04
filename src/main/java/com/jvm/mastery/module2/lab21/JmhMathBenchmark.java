package com.jvm.mastery.module2.lab21;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class JmhMathBenchmark {

    private double x = 42.0;

    // Cách 1: Sai lầm - Không dùng Blackhole, không return -> Bị JIT xóa code
    @Benchmark
    public void testDeadCode() {
        double result = Math.sqrt(x) * Math.sin(x);
        // Không làm gì với result
    }

    // Cách 2: Chuẩn - Dùng return để báo JVM kết quả có người nhận
    @Benchmark
    public double testWithReturn() {
        return Math.sqrt(x) * Math.sin(x);
    }

    // Cách 3: Chuẩn tuyệt đối trong JMH - Dùng Blackhole để "nuốt" kết quả mà không tốn chi phí
    @Benchmark
    public void testWithBlackhole(Blackhole bh) {
        double result = Math.sqrt(x) * Math.sin(x);
        bh.consume(result);
    }
}
