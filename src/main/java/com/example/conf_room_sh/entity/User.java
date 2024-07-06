package com.example.conf_room_sh.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity<UUID> {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 512)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
