package com.example.conf_room_sh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "time_slots")
public class TimeSlot extends BaseEntity<UUID> {

    @PrePersist
    protected void onPrePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @Column(nullable = false)
    private Boolean avaliable;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ConferenceRoom room;

    public TimeSlot(LocalDateTime start, LocalDateTime end, Boolean avaliable, ConferenceRoom room) {
        this.start = start;
        this.end = end;
        this.avaliable = avaliable;
        this.room = room;
    }
}
