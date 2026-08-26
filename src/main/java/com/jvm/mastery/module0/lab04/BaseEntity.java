package com.jvm.mastery.module0.lab04;

import java.time.LocalDateTime;

/**
 * Lớp cha cơ sở cho tất cả các Entity trong hệ thống.
 * Mọi Entity có ID đều kế thừa từ đây.
 */
public abstract class BaseEntity<ID> {

    private ID id;
    private LocalDateTime createdAt;

    protected BaseEntity(ID id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
