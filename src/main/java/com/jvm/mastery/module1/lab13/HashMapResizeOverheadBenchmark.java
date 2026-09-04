package com.jvm.mastery.module1.lab13;

import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * =====================================================================
 * PHẦN 2: ĐO ĐẠC HIỆN TƯỢNG RESIZE & REHASHING (N = 100,000 PHẦN TỬ)
 * =====================================================================
 */
public class HashMapResizeOverheadBenchmark {

    public static void compareResizeVsInitialCapacity(int count) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 PHẦN 2: KHẢO SÁT HIỆN TƯỢNG RESIZE & REHASHING (N = %,d PHẦN TỬ)%n", count);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // 1. HashMap KHÔNG đặt Initial Capacity (Mặc định bắt đầu từ 16 buckets)
        long start = System.nanoTime();
        Map<Integer, Integer> naiveMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            naiveMap.put(i, i);
        }
        long naiveTimeNs = System.nanoTime() - start;
        long naiveMemoryBytes = GraphLayout.parseInstance(naiveMap).totalSize();

        // 2. HashMap ĐƯỢC tính toán Initial Capacity tối ưu: capacity / 0.75 + 1
        int calculatedCapacity = (int) Math.ceil(count / 0.75f) + 1;
        start = System.nanoTime();
        Map<Integer, Integer> optimizedMap = new HashMap<>(calculatedCapacity);
        for (int i = 0; i < count; i++) {
            optimizedMap.put(i, i);
        }
        long optimizedTimeNs = System.nanoTime() - start;
        long optimizedMemoryBytes = GraphLayout.parseInstance(optimizedMap).totalSize();

        // In kết quả so sánh
        System.out.printf("1️⃣ HashMap mặc định new HashMap<>() (Bị Resize 14 lần liên tiếp):%n");
        System.out.printf("   • Thời gian chèn: %8.2f ms%n", naiveTimeNs / 1_000_000.0);
        System.out.printf("   • Dung lượng RAM: %,12d bytes (~%6.2f MB)%n%n", naiveMemoryBytes, naiveMemoryBytes / (1024.0 * 1024.0));

        System.out.printf("2️⃣ HashMap tối ưu new HashMap<>(%,d) (KHÔNG BỊ RESIZE LẦN NÀO):%n", calculatedCapacity);
        System.out.printf("   • Thời gian chèn: %8.2f ms (Nhanh hơn %.1fx!)%n",
                optimizedTimeNs / 1_000_000.0, (double) naiveTimeNs / optimizedTimeNs);
        System.out.printf("   • Dung lượng RAM: %,12d bytes (~%6.2f MB)%n", optimizedMemoryBytes, optimizedMemoryBytes / (1024.0 * 1024.0));
    }
}
