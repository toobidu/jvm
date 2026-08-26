package com.jvm.mastery.module0.lab04;

public class UserEntity extends BaseEntity<Long> {

    private String username;
    private String email;

    public UserEntity(Long id, String username, String email) {
        super(id);
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, username='%s', email='%s']", getId(), username, email);
    }
}
