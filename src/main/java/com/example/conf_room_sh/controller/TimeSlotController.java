package com.example.conf_room_sh.controller;

import com.example.conf_room_sh.dto.timeslot.TimeSlotDto;
import com.example.conf_room_sh.service.timeslot.TimeSlotService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(path = "/timeslots")
@AllArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping
    public Page<TimeSlotDto> findAll(@PageableDefault Pageable pageable) {
        return timeSlotService.getAllPageable(pageable);
    }

    public TimeSlotDto find(@PathVariable UUID timeSlotId) {
        return timeSlotService.findById(timeSlotId);
    }

    @PostMapping
    public TimeSlotDto create(@RequestBody TimeSlotDto timeSlotDto) {
        return timeSlotService.create(timeSlotDto);
    }

    @PatchMapping(path = "/{timeSlotId}")
    public TimeSlotDto update(@PathVariable UUID timeSlotId, @RequestBody TimeSlotDto timeSlotDto) {
        return timeSlotService.update(timeSlotId, timeSlotDto);
    }

    @DeleteMapping(path = "/{timeSlotId}")
    public void deleteById(@PathVariable UUID timeSlotId) {
        timeSlotService.deleteById(timeSlotId);
    }

    @GetMapping("/room")
    public Page<TimeSlotDto> findAllTimeSlotsByRoom(@RequestParam(defaultValue = "0") Integer from,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          @RequestParam() UUID roomId,
                                          @RequestParam() String startTime,
                                          @RequestParam(required = false, defaultValue = "PT15M") String duration) {
        return timeSlotService.findAllByRoom(from, size, roomId, LocalDateTime.parse(startTime), Duration.parse(duration));
    }
}
