package com.example.conf_room_sh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ConferenceRoom extends BaseEntity<UUID> {
    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
