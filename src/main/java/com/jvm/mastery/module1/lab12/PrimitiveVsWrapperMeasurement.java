package com.jvm.mastery.module1.lab12;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * =====================================================================
 * PHẦN 1: ĐO ĐẠC ĐỐI TƯỢNG ĐƠN LẺ (int vs Integer)
 * =====================================================================
 */
public class PrimitiveVsWrapperMeasurement {

    public static void measureSingleObjects() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📊 PHẦN 1: GIẢI PHẪU CHI PHÍ ĐỐI TƯỢNG ĐƠN LẺ (int vs Integer)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // 1. int nguyên thủy: không phải Object, nằm trên Stack hoặc nhét trong mảng (4 bytes)
        System.out.println("1️⃣ KIỂU NGUYÊN THỦY int:");
        System.out.println("  • Dung lượng thuần túy: 4 bytes (Chỉ chứa giá trị số, không có Header, không có Padding)\n");

        // 2. Integer Wrapper Object: là một Object đầy đủ trên Heap
        Integer boxedInteger = Integer.valueOf(42);
        System.out.println("2️⃣ ĐỐI TƯỢNG Integer (Wrapper Class):");
        System.out.println(ClassLayout.parseInstance(boxedInteger).toPrintable());
        
        System.out.println("👉 NHẬN XÉT: Để lưu một số nguyên 4 bytes, Integer Object tốn tới 16 bytes (Gấp 4 lần)!");
    }
}
