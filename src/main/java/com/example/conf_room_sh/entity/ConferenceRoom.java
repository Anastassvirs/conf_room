package com.example.conf_room_sh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conference_rooms")
public class ConferenceRoom extends BaseEntity<UUID> {

    @PrePersist
    protected void onPrePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

}
