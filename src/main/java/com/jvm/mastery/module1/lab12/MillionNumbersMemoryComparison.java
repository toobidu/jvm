package com.jvm.mastery.module1.lab12;

import org.openjdk.jol.info.GraphLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * =====================================================================
 * PHẦN 2: ĐO ĐẠC TOÀN BỘ CÂY ĐỐI TƯỢNG (DEEP OBJECT GRAPH) VỚI 1 TRIỆU PHẦN TỬ
 * =====================================================================
 */
public class MillionNumbersMemoryComparison {

    public static void compareMillionElements(int count) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("📊 PHẦN 2: SO SÁNH DUNG LƯỢNG TRÊN HEAP (N = %,d PHẦN TỬ)%n", count);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        // 1. Mảng nguyên thủy: int[]
        int[] primitiveArray = new int[count];
        for (int i = 0; i < count; i++) {
            primitiveArray[i] = i;
        }
        long primitiveBytes = GraphLayout.parseInstance((Object) primitiveArray).totalSize();
        double primitiveMb = primitiveBytes / (1024.0 * 1024.0);

        // 2. Mảng đối tượng: Integer[] (Mỗi phần tử là 1 con trỏ trỏ tới 1 Integer Object)
        Integer[] wrapperArray = new Integer[count];
        for (int i = 0; i < count; i++) {
            // Dùng new Integer(...) hoặc giá trị ngoài cache -128..127 để tạo object thật
            wrapperArray[i] = Integer.valueOf(i + 1000); 
        }
        long wrapperBytes = GraphLayout.parseInstance((Object) wrapperArray).totalSize();
        double wrapperMb = wrapperBytes / (1024.0 * 1024.0);

        // 3. Danh sách: ArrayList<Integer>
        List<Integer> arrayList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            arrayList.add(Integer.valueOf(i + 1000));
        }
        long listBytes = GraphLayout.parseInstance(arrayList).totalSize();
        double listMb = listBytes / (1024.0 * 1024.0);

        // In kết quả so sánh
        System.out.printf("1️⃣ Mảng nguyên thủy int[%d]:        %,12d bytes (~%6.2f MB)%n", count, primitiveBytes, primitiveMb);
        System.out.printf("2️⃣ Mảng đối tượng Integer[%d]:     %,12d bytes (~%6.2f MB) -> Gấp %.1fx int[]!%n",
                count, wrapperBytes, wrapperMb, (double) wrapperBytes / primitiveBytes);
        System.out.printf("3️⃣ Danh sách ArrayList<Integer>(%d): %,12d bytes (~%6.2f MB) -> Gấp %.1fx int[]!%n",
                count, listBytes, listMb, (double) listBytes / primitiveBytes);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔍 PHÂN TÍCH CHI TIẾT TẠI SAO ArrayList<Integer> TỐN GẦP NHIỀU LẦN BỘ NHỚ:");
        System.out.println("  1. Dữ liệu thực tế cần lưu: 1 triệu số int * 4 bytes = 4.0 MB.");
        System.out.println("  2. Mảng con trỏ Integer[]: 1 triệu con trỏ * 4 bytes = 4.0 MB.");
        System.out.println("  3. 1 triệu đối tượng Integer trên Heap: 1 triệu * 16 bytes = 16.0 MB.");
        System.out.println("  4. ArrayList Object + Mảng bên trong: ~4.0 MB.");
        System.out.println("  👉 TỔNG CỘNG: ~24.0 MB (Lãng phí tới 20.0 MB chỉ để chứa Header, Con trỏ và Rác)!");
    }
}
