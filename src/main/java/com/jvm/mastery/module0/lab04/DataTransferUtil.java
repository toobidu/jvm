package com.jvm.mastery.module0.lab04;

import java.util.List;

/**
 * =====================================================================
 * MINH HỌA NGUYÊN TẮC PECS: Producer Extends, Consumer Super
 * =====================================================================
 *
 * 1. "? extends T" (Producer / Nguồn cung cấp):
 *    - Danh sách này "sản xuất" dữ liệu cho bạn đọc ra (read-only).
 *    - BẠN CHỈ ĐƯỢC ĐỌC RA, KHÔNG ĐƯỢC ADD THÊM PHẦN TỬ MỚI VÀO.
 *
 * 2. "? super T" (Consumer / Nơi tiêu thụ):
 *    - Danh sách này "tiêu thụ" dữ liệu bạn ghi vào (write-only).
 *    - BẠN ĐƯỢC PHÉP ADD PHẦN TỬ KIỂU T VÀO DANH SÁCH NÀY.
 */
public class DataTransferUtil {

    /**
     * Copy toàn bộ phần tử từ source sang destination một cách an toàn.
     * Đúng theo mẫu chuẩn của java.util.Collections.copy(...) trong JDK!
     */
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            // source đóng vai trò Producer: ta ĐỌC dữ liệu từ source
            // destination đóng vai trò Consumer: ta GHI dữ liệu vào destination
            destination.add(item);
        }
    }

    /**
     * In danh sách các Entity (Chỉ cần đọc ra -> Dùng ? extends)
     */
    public static void printAll(List<? extends BaseEntity<?>> entities) {
        for (BaseEntity<?> entity : entities) {
            System.out.println("  • " + entity.toString() + " (Tạo lúc: " + entity.getCreatedAt() + ")");
        }
    }
}
