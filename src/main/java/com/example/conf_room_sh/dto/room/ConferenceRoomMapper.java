package com.example.conf_room_sh.dto.room;

import com.example.conf_room_sh.entity.ConferenceRoom;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomMapper {
    public static ConferenceRoomDto toConferenceRoomDto(ConferenceRoom conferenceRoom) {
        return new ConferenceRoomDto(
                conferenceRoom.getName(),
                conferenceRoom.getDescription()
        );
    }

    public static ConferenceRoom toConferenceRoom(ConferenceRoomDto conferenceRoomDto) {
        return new ConferenceRoom(
                conferenceRoomDto.getName(),
                conferenceRoomDto.getDescription()
        );
    }

    public static Page<ConferenceRoomDto> toPageConferenceRoomDto(Page<ConferenceRoom> conferenceRoomsPage) {
        return conferenceRoomsPage.map(ConferenceRoomMapper::toConferenceRoomDto);
    }
}
