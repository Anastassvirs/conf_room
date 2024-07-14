package com.example.conf_room_sh.service.room;

import com.example.conf_room_sh.dto.room.ConferenceRoomDto;
import com.example.conf_room_sh.dto.room.ConferenceRoomMapper;
import com.example.conf_room_sh.entity.Booking;
import com.example.conf_room_sh.entity.ConferenceRoom;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.exception.SaveEntityException;
import com.example.conf_room_sh.repository.ConferenceRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;

    @Autowired
    public ConferenceRoomServiceImpl(ConferenceRoomRepository conferenceRoomRepository, ConferenceRoomMapper conferenceRoomMapper) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.conferenceRoomMapper = conferenceRoomMapper;
    }


    @Override
    public Page<ConferenceRoomDto> getAllPageable(Pageable pageable) {
        return conferenceRoomMapper.toPageConferenceRoomDto(conferenceRoomRepository.findAll(pageable));
    }

    @Override
    public ConferenceRoomDto findById(UUID id) {
        return conferenceRoomMapper.toConferenceRoomDto(getById(id));
    }

    @Override
    public ConferenceRoom getById(UUID id) {
        return conferenceRoomRepository.findById(id).orElseThrow(() -> new NotFoundAnythingException("Переговорной с данным id не существует"));
    }

    @Transactional
    @Override
    public ConferenceRoomDto create(ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoom = conferenceRoomMapper.toConferenceRoom(conferenceRoomDto);
        try {
            log.debug("Добавлена новая переговорная: {}", conferenceRoom);
            return conferenceRoomMapper.toConferenceRoomDto(conferenceRoomRepository.save(conferenceRoom));
        } catch (Exception e) {
            log.debug("Произошла ошибка: Неправильно заполнены поля создаваемой переговорной");
            throw new SaveEntityException("Неправильно заполнены поля создаваемой переговорной");
        }
    }

    @Transactional
    @Override
    public ConferenceRoomDto update(UUID id, ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoom = conferenceRoomMapper.toConferenceRoom(conferenceRoomDto);
        try {
            log.debug("Добавлена новая переговорная: {}", conferenceRoom);
            return conferenceRoomMapper.toConferenceRoomDto(conferenceRoomRepository.save(conferenceRoom));
        } catch (Exception e) {
            log.debug("Произошла ошибка: Неправильно заполнены поля создаваемой переговорной");
            throw new SaveEntityException("Неправильно заполнены поля создаваемой переговорной");
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        conferenceRoomRepository.deleteById(id);
    }

    @Override
    public boolean existById(UUID id) {
        for (ConferenceRoom oldConferenceRoom : conferenceRoomRepository.findAll()) {
            if (Objects.equals(oldConferenceRoom.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
