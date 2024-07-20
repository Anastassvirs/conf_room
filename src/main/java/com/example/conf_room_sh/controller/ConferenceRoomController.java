package com.example.conf_room_sh.controller;

import com.example.conf_room_sh.dto.room.ConferenceRoomDto;
import com.example.conf_room_sh.service.room.ConferenceRoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/rooms")
@AllArgsConstructor
public class ConferenceRoomController {
    private final ConferenceRoomService conferenceRoomService;

    @GetMapping
    public Page<ConferenceRoomDto> findAll(@PageableDefault Pageable pageable) {
        return conferenceRoomService.getAllPageable(pageable);
    }

    public ConferenceRoomDto find(@PathVariable UUID roomId) {
        return conferenceRoomService.findById(roomId);
    }

    @PostMapping
    public ConferenceRoomDto create(@RequestBody ConferenceRoomDto conferenceRoomDto) {
        return conferenceRoomService.create(conferenceRoomDto);
    }

    @PatchMapping(path = "/{roomId}")
    public ConferenceRoomDto update(@PathVariable UUID roomId, @RequestBody ConferenceRoomDto conferenceRoomDto) {
        return conferenceRoomService.update(roomId, conferenceRoomDto);
    }

    @DeleteMapping(path = "/{roomId}")
    public void deleteById(@PathVariable UUID roomId) {
        conferenceRoomService.deleteById(roomId);
    }
}
