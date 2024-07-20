package com.example.conf_room_sh.dto.timeslot;

import com.example.conf_room_sh.entity.TimeSlot;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.repository.ConferenceRoomRepository;
import com.example.conf_room_sh.repository.TimeSlotRepository;
import com.example.conf_room_sh.service.room.ConferenceRoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TimeSlotMapper {
    private final ConferenceRoomRepository conferenceRoomRepository;
    public TimeSlotDto toTimeSlotDto(TimeSlot timeSlot) {
        return new TimeSlotDto(
                timeSlot.getStart(),
                timeSlot.getEnd(),
                timeSlot.getAvaliable(),
                timeSlot.getRoom().getId()
        );
    }

    public TimeSlot toTimeSlot(TimeSlotDto timeSlotDto) {
        return new TimeSlot(
                timeSlotDto.getStart(),
                timeSlotDto.getEnd(),
                timeSlotDto.getAvaliable(),
                conferenceRoomRepository.findById(timeSlotDto.getRoom_id()).orElseThrow(() ->
                        new NotFoundAnythingException("Переговорной с введенным id не существует"))


        );
    }

    public Page<TimeSlotDto> toPageTimeSlotDto(Page<TimeSlot> timeSlotsPage) {
        return timeSlotsPage.map(this::toTimeSlotDto);
    }
}
