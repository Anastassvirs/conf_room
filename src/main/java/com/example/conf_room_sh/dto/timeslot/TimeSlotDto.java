package com.example.conf_room_sh.dto.timeslot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TimeSlotDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean avaliable;
    private UUID room_id;
}
