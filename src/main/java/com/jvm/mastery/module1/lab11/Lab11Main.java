package com.jvm.mastery.module1.lab11;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class Lab11Main {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 1 — LAB 1.1: GIẢI PHẪU OBJECT HEADER & ALIGNMENT BẰNG JOL      ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝\n");

        // In thông tin chi tiết về kiến trúc Máy ảo JVM đang chạy
        System.out.println("🖥️ THÔNG TIN MÁY ẢO JVM:");
        System.out.println(VM.current().details());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // 1. Giải phẫu Object rỗng
        System.out.println("1️⃣ GIẢI PHẪU OBJECT RỖNG (EmptyObject):");
        System.out.println(ClassLayout.parseClass(EmptyObject.class).toPrintable());

        // 2. Giải phẫu Object chứa 1 int (4 bytes)
        System.out.println("\n2️⃣ GIẢI PHẪU OBJECT CHỨA 1 INT (SingleIntObject):");
        System.out.println(ClassLayout.parseClass(SingleIntObject.class).toPrintable());

        // 3. Giải phẫu Object chứa 1 long (8 bytes)
        System.out.println("\n3️⃣ GIẢI PHẪU OBJECT CHỨA 1 LONG (SingleLongObject):");
        System.out.println(ClassLayout.parseClass(SingleLongObject.class).toPrintable());

        // 4. Giải phẫu Object chứa 2 booleans (2 bytes)
        System.out.println("\n4️⃣ GIẢI PHẪU OBJECT CHỨA 2 BOOLEANS (TwoBooleansObject):");
        System.out.println(ClassLayout.parseClass(TwoBooleansObject.class).toPrintable());

        // 5. Giải phẫu Thứ tự đóng gói Field của JVM (Field Packing Reordering)
        System.out.println("\n5️⃣ HIỆN TƯỢNG JVM TỰ ĐỘNG ĐẢO THỨ TỰ FIELD ĐỂ TIẾT KIỆM BỘ NHỚ:");
        System.out.println(ClassLayout.parseClass(FieldPackingOrderObject.class).toPrintable());
    }
}
