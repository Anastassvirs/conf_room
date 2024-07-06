package com.example.conf_room_sh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class TimeSlot extends BaseEntity<UUID> {

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @Column
    private Boolean avaliable;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ConferenceRoom room;
}
