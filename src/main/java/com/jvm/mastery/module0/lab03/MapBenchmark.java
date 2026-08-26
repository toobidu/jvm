package com.jvm.mastery.module0.lab03;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * =====================================================================
 * BENCHMARK 2: HashMap vs LinkedHashMap vs TreeMap
 * =====================================================================
 * 1. HashMap: Bảng băm O(1) - Không đảm bảo thứ tự
 * 2. LinkedHashMap: Bảng băm + Danh sách liên kết kép O(1) - Đảm bảo thứ tự chèn (Insertion Order)
 * 3. TreeMap: Cây Đỏ-Đen (Red-Black Tree) O(log N) - Tự động sắp xếp Key tăng dần
 */
public class MapBenchmark {

    public static void runBenchmark(int elementCount) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 SO SÁNH HASHMAP vs LINKEDHASHMAP vs TREEMAP (N = %,d)%n", elementCount);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // 1. Đo thao tác PUT (Thêm mới)
        long start = System.nanoTime();
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < elementCount; i++) {
            hashMap.put(i, "Value_" + i);
        }
        long hashPut = System.nanoTime() - start;

        start = System.nanoTime();
        Map<Integer, String> linkedMap = new LinkedHashMap<>();
        for (int i = 0; i < elementCount; i++) {
            linkedMap.put(i, "Value_" + i);
        }
        long linkedPut = System.nanoTime() - start;

        start = System.nanoTime();
        Map<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < elementCount; i++) {
            treeMap.put(i, "Value_" + i);
        }
        long treePut = System.nanoTime() - start;

        System.out.printf("👉 Thao tác PUT:     HashMap: %6.2f ms | LinkedHashMap: %6.2f ms | TreeMap: %6.2f ms%n",
                hashPut / 1e6, linkedPut / 1e6, treePut / 1e6);

        // 2. Đo thao tác GET (Truy xuất)
        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            String v = hashMap.get(i);
        }
        long hashGet = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            String v = linkedMap.get(i);
        }
        long linkedGet = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            String v = treeMap.get(i);
        }
        long treeGet = System.nanoTime() - start;

        System.out.printf("👉 Thao tác GET:     HashMap: %6.2f ms | LinkedHashMap: %6.2f ms | TreeMap: %6.2f ms%n",
                hashGet / 1e6, linkedGet / 1e6, treeGet / 1e6);

        // 3. Minh họa đặc tính THỨ TỰ (Ordering Behavior)
        System.out.println("\n🔍 MINH HỌA THỨ TỰ CỦA 3 LOẠI MAP:");
        Map<Integer, String> h = new HashMap<>();
        Map<Integer, String> lh = new LinkedHashMap<>();
        Map<Integer, String> t = new TreeMap<>();

        int[] sampleKeys = {50, 10, 80, 20, 90};
        for (int k : sampleKeys) {
            h.put(k, "V" + k);
            lh.put(k, "V" + k);
            t.put(k, "V" + k);
        }

        System.out.println("  • Input gốc:             [50, 10, 80, 20, 90]");
        System.out.println("  • HashMap (Xáo trộn):    " + h.keySet());
        System.out.println("  • LinkedHashMap (Giữ):   " + lh.keySet() + " (Đúng thứ tự chèn!)");
        System.out.println("  • TreeMap (Sắp xếp):     " + t.keySet() + " (Tự sort tăng dần!)");
    }
}
