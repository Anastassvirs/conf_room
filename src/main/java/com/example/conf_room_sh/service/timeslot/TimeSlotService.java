package com.example.conf_room_sh.service.timeslot;

import com.example.conf_room_sh.dto.timeslot.TimeSlotDto;
import com.example.conf_room_sh.entity.TimeSlot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TimeSlotService {

    Page<TimeSlotDto> getAllPageable(Pageable pageable);

    TimeSlotDto findById(UUID id);

    TimeSlot getById(UUID id);

    TimeSlotDto create(TimeSlotDto TimeSlotDto);

    TimeSlotDto update(UUID id, TimeSlotDto timeSlotDto);

    void deleteById(UUID id);
}
