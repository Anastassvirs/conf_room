package com.example.conf_room_sh.service.timeslot;

import com.example.conf_room_sh.dto.timeslot.TimeSlotDto;
import com.example.conf_room_sh.dto.timeslot.TimeSlotMapper;
import com.example.conf_room_sh.entity.TimeSlot;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.exception.SaveEntityException;
import com.example.conf_room_sh.exception.WrongParametersException;
import com.example.conf_room_sh.repository.ConferenceRoomRepository;
import com.example.conf_room_sh.repository.TimeSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final TimeSlotMapper timeSlotMapper;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, ConferenceRoomRepository conferenceRoomRepository, TimeSlotMapper timeSlotMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    public Page<TimeSlotDto> getAllPageable(Pageable pageable) {
        return timeSlotMapper.toPageTimeSlotDto(timeSlotRepository.findAll(pageable));
    }

    @Override
    public TimeSlotDto findById(UUID id) {
        return timeSlotMapper.toTimeSlotDto(getById(id));
    }

    @Override
    public Page<TimeSlotDto> findAllByRoom(Integer from, Integer size, UUID roomId, boolean onlyAvaliable, LocalDate date) {
        if (!conferenceRoomRepository.existsById(roomId)) {
            throw new NotFoundAnythingException("Переговорной, к которой требуется найти таймслоты, не существует");
        }
        Pageable pageable = PageRequest.of(from / size, size);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX); //TODO: Хз, мб можно лучше как-то сделать. надо подумать
        if (onlyAvaliable) {
            return timeSlotMapper.toPageTimeSlotDto(timeSlotRepository.findAllByAvaliableAndAndRoomIdAndStartAfterAndEndBefore(onlyAvaliable, roomId, startOfDay, endOfDay, pageable));
        } else {
            return timeSlotMapper.toPageTimeSlotDto(timeSlotRepository.findAllByRoomIdAndStartAfterAndEndBefore(roomId, startOfDay, endOfDay, pageable));
        }
    }

    @Override
    public TimeSlot getById(UUID id) {
        return timeSlotRepository.findById(id).orElseThrow(() -> new NotFoundAnythingException("Таймслота с данным id не существует"));
    }

    @Transactional
    @Override
    public TimeSlotDto create(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toTimeSlot(timeSlotDto);

        if (Objects.isNull(timeSlotDto.getStart()) || Objects.isNull(timeSlotDto.getEnd()) ||
                Objects.isNull(timeSlotDto.getRoom_id()) || !conferenceRoomRepository.existsById(timeSlotDto.getRoom_id())) {
            throw new WrongParametersException("Неправильно заполнены поля создаваемого пользователя");
        }
        log.debug("Добавлен новый таймслот: {}", timeSlot);
        try {
            return timeSlotMapper.toTimeSlotDto(timeSlotRepository.save(timeSlot));
        } catch (Exception e) {
            log.debug("Произошла ошибка: Неправильно заполнены поля создаваемого таймслота");
            throw new SaveEntityException("Неправильно заполнены поля создаваемого таймслота");
        }
    }

    @Transactional
    @Override
    public TimeSlotDto update(UUID id, TimeSlotDto timeSlotDto) {
        if(timeSlotRepository.existsById(id)) {
            TimeSlot timeSlot = timeSlotMapper.toTimeSlot(timeSlotDto);
            try {
                log.debug("Обновлён таймслот: {}", timeSlot);
                return timeSlotMapper.toTimeSlotDto(timeSlotRepository.save(timeSlot));
            } catch (Exception e) {
                log.debug("Произошла ошибка: Неправильно заполнены поля создаваемой переговорной");
                throw new SaveEntityException("Неправильно заполнены поля создаваемой переговорной");
            }
        } else {
            throw new NotFoundAnythingException("Таймслота с введённым id не существует");
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        timeSlotRepository.deleteById(id);
    }
}
