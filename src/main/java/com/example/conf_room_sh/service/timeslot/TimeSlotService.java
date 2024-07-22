package com.example.conf_room_sh.service.timeslot;

import com.example.conf_room_sh.dto.timeslot.TimeSlotDto;
import com.example.conf_room_sh.entity.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public interface TimeSlotService {

    Page<TimeSlotDto> getAllPageable(Pageable pageable);

    TimeSlotDto findById(UUID id);

    Page<TimeSlotDto> findAllByRoom(Integer from, Integer size, UUID roomId, LocalDateTime startTime, Duration duration);

    TimeSlot getById(UUID id);

    TimeSlotDto create(TimeSlotDto TimeSlotDto);

    TimeSlotDto update(UUID id, TimeSlotDto timeSlotDto);

    void deleteById(UUID id);
}
