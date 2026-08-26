package com.jvm.mastery.module0.lab04;

import java.util.ArrayList;
import java.util.List;

public class Lab04Main {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   MODULE 0 — LAB 0.4: GENERICS, TYPE ERASURE & BOUNDS         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // 1. Khởi tạo Generic Repository cho User (ID = Long)
        System.out.println("1️⃣ REPOSITORY CHO USER (ID = Long):");
        GenericRepository<UserEntity, Long> userRepo = new InMemoryGenericRepository<>();
        userRepo.save(new UserEntity(1L, "nguyenvana", "vana@company.com"));
        userRepo.save(new UserEntity(2L, "tranthib", "thib@company.com"));

        var user = userRepo.findById(1L);
        user.ifPresent(u -> System.out.println("  Tìm thấy: " + u));
        System.out.println("  Tổng số user: " + userRepo.count());

        // Gọi thử nghiệm findByIds
        System.out.println("  👉 Tìm nhiều user (ID 1 & 2): " + userRepo.findByIds(List.of(1L, 2L)));

        System.out.println("\n─────────────────────────────────────────────────────────\n");

        // 2. Khởi tạo Generic Repository cho Product (ID = String SKU)
        System.out.println("2️⃣ REPOSITORY CHO PRODUCT (ID = String SKU):");
        GenericRepository<ProductEntity, String> productRepo = new InMemoryGenericRepository<>();
        productRepo.save(new ProductEntity("SKU_LAPTOP_01", "MacBook Pro M3", 45_000_000));
        productRepo.save(new ProductEntity("SKU_PHONE_02", "iPhone 15 Pro", 28_000_000));

        var product = productRepo.findById("SKU_LAPTOP_01");
        product.ifPresent(p -> System.out.println("  Tìm thấy: " + p));
        System.out.println("  Tổng số sản phẩm: " + productRepo.count());

        System.out.println("\n─────────────────────────────────────────────────────────\n");

        // 3. Minh họa nguyên tắc PECS (Producer Extends, Consumer Super)
        System.out.println("3️⃣ MINH HỌA NGUYÊN TẮC PECS (Copy danh sách đa kiểu):");
        
        List<UserEntity> activeUsers = List.of(
            new UserEntity(10L, "alex_dev", "alex@tech.io"),
            new UserEntity(11L, "sarah_qa", "sarah@tech.io")
        );

        // Danh sách đích chứa BaseEntity<?> (lớp cha) -> Hợp lệ với ? super UserEntity
        List<BaseEntity<?>> allEntities = new ArrayList<>();
        
        // Copy từ List<UserEntity> sang List<BaseEntity<?>>
        DataTransferUtil.copy(activeUsers, allEntities);

        System.out.println("  Danh sách BaseEntity sau khi copy:");
        DataTransferUtil.printAll(allEntities);

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("✅ Chạy thử nghiệm thành công!");
    }
}
