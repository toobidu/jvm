package com.jvm.mastery.module0.lab03;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * =====================================================================
 * BENCHMARK 3: HashSet vs LinkedHashSet vs TreeSet
 * =====================================================================
 * Set thực chất bên trong là MAP (HashSet bọc HashMap, TreeSet bọc TreeMap)!
 */
public class SetBenchmark {

    public static void runBenchmark(int elementCount) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 SO SÁNH HASHSET vs LINKEDHASHSET vs TREESET (N = %,d)%n", elementCount);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // 1. Đo ADD
        long start = System.nanoTime();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < elementCount; i++) {
            hashSet.add(i);
        }
        long hashAdd = System.nanoTime() - start;

        start = System.nanoTime();
        Set<Integer> linkedSet = new LinkedHashSet<>();
        for (int i = 0; i < elementCount; i++) {
            linkedSet.add(i);
        }
        long linkedAdd = System.nanoTime() - start;

        start = System.nanoTime();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < elementCount; i++) {
            treeSet.add(i);
        }
        long treeAdd = System.nanoTime() - start;

        System.out.printf("👉 Thao tác ADD:     HashSet: %6.2f ms | LinkedHashSet: %6.2f ms | TreeSet: %6.2f ms%n",
                hashAdd / 1e6, linkedAdd / 1e6, treeAdd / 1e6);

        // 2. Đo CONTAINS
        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            boolean b = hashSet.contains(i);
        }
        long hashContains = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            boolean b = linkedSet.contains(i);
        }
        long linkedContains = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            boolean b = treeSet.contains(i);
        }
        long treeContains = System.nanoTime() - start;

        System.out.printf("👉 Thao tác CONTAINS:HashSet: %6.2f ms | LinkedHashSet: %6.2f ms | TreeSet: %6.2f ms%n",
                hashContains / 1e6, linkedContains / 1e6, treeContains / 1e6);
    }
}
