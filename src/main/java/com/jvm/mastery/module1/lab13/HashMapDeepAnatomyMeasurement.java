package com.jvm.mastery.module1.lab13;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * =====================================================================
 * PHẦN 1: GIẢI PHẪU CÂY ĐỐI TƯỢNG HASHMAP (DEEP ANATOMY)
 * =====================================================================
 */
public class HashMapDeepAnatomyMeasurement {

    public static void inspectHashMapStructure() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📊 PHẦN 1: GIẢI PHẪU CHI TIẾT TỪNG THÀNH PHẦN CỦA HASHMAP TRONG RAM");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // 1. HashMap vừa mới khởi tạo (Chưa put gì)
        Map<Integer, Integer> emptyMap = new HashMap<>();
        long emptyMapBytes = GraphLayout.parseInstance(emptyMap).totalSize();
        System.out.println("1️⃣ HASHMAP VỪA NEW (RỖNG):");
        System.out.println("  • Kích thước HashMap Object: " + ClassLayout.parseInstance(emptyMap).instanceSize() + " bytes");
        System.out.println("  • Tổng dung lượng toàn bộ cây: " + emptyMapBytes + " bytes");
        System.out.println("  👉 NHẬN XÉT: Java dùng cơ chế Lazy Init (bộ nhớ mâm chứa table chưa được cấp phát)!\n");

        // 2. HashMap sau khi put đúng 1 phần tử: map.put(1000, 2000)
        Map<Integer, Integer> singleElementMap = new HashMap<>();
        singleElementMap.put(1000, 2000);
        long singleElementBytes = GraphLayout.parseInstance(singleElementMap).totalSize();

        System.out.println("2️⃣ HASHMAP SAU KHI PUT ĐÚNG 1 CẶP (1000 -> 2000):");
        System.out.println("  • Tổng dung lượng toàn bộ cây trong RAM: " + singleElementBytes + " BYTES!");
        System.out.println("\n  📋 MỔ XẺ CHI TIẾT CÂY ĐỐI TƯỢNG (GRAPH FOOTPRINT):");
        System.out.println(GraphLayout.parseInstance(singleElementMap).toFootprint());
    }
}
