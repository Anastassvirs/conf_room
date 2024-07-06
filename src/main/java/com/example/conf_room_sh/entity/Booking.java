package com.example.conf_room_sh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Booking extends BaseEntity<UUID> {

    @Column
    private String comment;

    public Booking(String comment) {
        this.comment = comment;
    }
}
