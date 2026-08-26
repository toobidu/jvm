package com.jvm.mastery.module0.lab04;

import java.util.List;
import java.util.Optional;

/**
 * =====================================================================
 * BOUNDED TYPE PARAMETER: T extends BaseEntity<ID>
 * =====================================================================
 *
 * Ý nghĩa:
 * 1. "T extends BaseEntity<ID>" (Upper Bound): Bắt buộc T phải là con của BaseEntity.
 *    Nhờ vậy, bên trong Repository ta có thể gọi method entity.getId() mà không sợ lỗi!
 * 2. ID: Kiểu dữ liệu của khóa chính (Long, String, UUID...).
 *
 * Đây chính là kiến trúc của Spring Data JpaRepository<T, ID>!
 */
public interface GenericRepository<T extends BaseEntity<ID>, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    boolean deleteById(ID id);

    long count();

    List<T> findByIds(List<ID> ids);
}
