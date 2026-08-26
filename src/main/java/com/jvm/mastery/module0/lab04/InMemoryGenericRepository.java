package com.jvm.mastery.module0.lab04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cài đặt Generic Repository lưu trữ trên bộ nhớ RAM (In-Memory).
 * Dùng ConcurrentHashMap đảm bảo an toàn đa luồng.
 */
public class InMemoryGenericRepository<T extends BaseEntity<ID>, ID> implements GenericRepository<T, ID> {

    // Kho lưu trữ trong RAM: ID -> Entity
    private final Map<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        // Nhờ ràng buộc T extends BaseEntity<ID>, ta gọi được getId()!
        ID id = entity.getId();
        if (id == null) {
            throw new IllegalArgumentException("Entity ID cannot be null");
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(ID id) {
        if (id == null) {
            return false;
        }
        return storage.remove(id) != null;
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public List<T> findByIds(List<ID> ids) {
        List<T> result = new ArrayList<>();
        if (ids == null || ids.isEmpty()) return result;
        for (ID id : ids) {
            T entity = storage.get(id);
            if (entity != null) {
                result.add(entity);
            }
        }
        return result;
    }
}
