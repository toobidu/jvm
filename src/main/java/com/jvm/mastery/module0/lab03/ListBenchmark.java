package com.jvm.mastery.module0.lab03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * =====================================================================
 * BENCHMARK 1: ArrayList vs LinkedList
 * =====================================================================
 * Đo 4 thao tác cốt lõi:
 * 1. Chèn vào cuối (Append: add)
 * 2. Chèn vào đầu (Insert at head: add(0, x))
 * 3. Truy cập ngẫu nhiên (Random access: get(i))
 * 4. Duyệt tuần tự (Sequential Traversal: for-each sum)
 */
public class ListBenchmark {

    public static void runBenchmark(int elementCount) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 SO SÁNH ARRAYLIST VS LINKEDLIST (N = %,d phần tử)%n", elementCount);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Warm-up JVM
        warmUp();

        // 1. Thao tác 1: Chèn vào cuối danh sách (Append)
        long start = System.nanoTime();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < elementCount; i++) {
            arrayList.add(i);
        }
        long arrayListAppendTime = System.nanoTime() - start;

        start = System.nanoTime();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < elementCount; i++) {
            linkedList.add(i);
        }
        long linkedListAppendTime = System.nanoTime() - start;

        printResult("1. Chèn vào cuối (add)", arrayListAppendTime, linkedListAppendTime);

        // 2. Thao tác 2: Duyệt toàn bộ danh sách (Iterate & Sum) - Quan trọng nhất!
        start = System.nanoTime();
        long arraySum = 0;
        for (int val : arrayList) {
            arraySum += val;
        }
        long arrayListIterateTime = System.nanoTime() - start;

        start = System.nanoTime();
        long linkedSum = 0;
        for (int val : linkedList) {
            linkedSum += val;
        }
        long linkedListIterateTime = System.nanoTime() - start;

        printResult("2. Duyệt tuần tự (for-each sum)", arrayListIterateTime, linkedListIterateTime);

        // 3. Thao tác 3: Truy cập ngẫu nhiên (get by index) - Giới hạn N nhỏ để tránh treo máy
        int lookupCount = Math.min(elementCount, 5_000);
        start = System.nanoTime();
        for (int i = 0; i < lookupCount; i++) {
            int val = arrayList.get(i);
        }
        long arrayListGetTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < lookupCount; i++) {
            int val = linkedList.get(i);
        }
        long linkedListGetTime = System.nanoTime() - start;

        printResult("3. Truy xuất get(i) [" + lookupCount + " lần]", arrayListGetTime, linkedListGetTime);

        // 4. Thao tác 4: Chèn vào đầu (add(0, x)) - Giới hạn 20,000 để tránh O(N^2) treo máy
        int insertHeadCount = Math.min(elementCount, 20_000);
        List<Integer> alHead = new ArrayList<>();
        start = System.nanoTime();
        for (int i = 0; i < insertHeadCount; i++) {
            alHead.add(0, i);
        }
        long arrayListHeadTime = System.nanoTime() - start;

        List<Integer> llHead = new LinkedList<>();
        start = System.nanoTime();
        for (int i = 0; i < insertHeadCount; i++) {
            llHead.add(0, i);
        }
        long linkedListHeadTime = System.nanoTime() - start;

        printResult("4. Chèn vào đầu add(0, x) [" + insertHeadCount + " lần]", arrayListHeadTime, linkedListHeadTime);
    }

    private static void printResult(String operation, long arrayTimeNs, long linkedTimeNs) {
        double arrayMs = arrayTimeNs / 1_000_000.0;
        double linkedMs = linkedTimeNs / 1_000_000.0;
        double ratio = (double) linkedTimeNs / arrayTimeNs;

        String winner = ratio >= 1.0 
                ? String.format("ArrayList NHANH HƠN %.1fx", ratio)
                : String.format("LinkedList NHANH HƠN %.1fx", 1.0 / ratio);

        System.out.printf("👉 %-38s | ArrayList: %8.2f ms | LinkedList: %8.2f ms | 🏆 %s%n",
                operation, arrayMs, linkedMs, winner);
    }

    private static void warmUp() {
        // Giúp JIT Compiler compile code trước khi đo đạc
        List<Integer> al = new ArrayList<>();
        List<Integer> ll = new LinkedList<>();
        for (int i = 0; i < 10_000; i++) {
            al.add(i);
            ll.add(i);
        }
    }
}
