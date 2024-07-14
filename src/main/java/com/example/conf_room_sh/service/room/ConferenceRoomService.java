package com.example.conf_room_sh.service.room;

import com.example.conf_room_sh.dto.room.ConferenceRoomDto;
import com.example.conf_room_sh.entity.ConferenceRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ConferenceRoomService {
    Page<ConferenceRoomDto> getAllPageable(Pageable pageable);

    ConferenceRoomDto findById(UUID id);

    ConferenceRoom getById(UUID id);

    ConferenceRoomDto create(ConferenceRoomDto conferenceRoomDto);

    ConferenceRoomDto update(UUID id, ConferenceRoomDto conferenceRoomDto);

    void deleteById(UUID id);

    boolean existById(UUID id);
}
