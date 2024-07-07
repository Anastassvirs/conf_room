package com.example.conf_room_sh.service.booking;

import com.example.conf_room_sh.dto.booking.BookingDto;
import com.example.conf_room_sh.dto.booking.BookingMapper;
import com.example.conf_room_sh.entity.Booking;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.exception.SaveEntityException;
import com.example.conf_room_sh.repository.BookingRepository;
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
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Page<BookingDto> getAllPageable(Pageable pageable) {
        return bookingMapper.toPageBookingDto(bookingRepository.findAll(pageable));
    }

    @Override
    public BookingDto findById(UUID id) {
        return bookingMapper.toBookingDto(getById(id));
    }

    @Override
    public Booking getById(UUID id) {
        return bookingRepository.findById(id).orElseThrow(() -> new NotFoundAnythingException("Брони с данным id не существует"));
    }

    @Transactional
    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        try {
            log.debug("Добавлена новая бронь: {}", booking);
            return bookingMapper.toBookingDto(bookingRepository.save(booking));
        } catch (Exception e) {
            log.debug("Произошла ошибка: Неправильно заполнены поля создаваемой брони");
            throw new SaveEntityException("Неправильно заполнены поля создаваемой брони");
        }
    }

    @Transactional
    @Override
    public BookingDto updateBooking(UUID id, BookingDto bookingDto) {
        Booking booking = getById(id);
        Optional.ofNullable(bookingDto.getComment()).ifPresent(booking::setComment);
        if (bookingExistById(id)) {
            log.debug("Обновалена бронь: {}", booking);
            return bookingMapper.toBookingDto(bookingRepository.save(booking));
        } else {
            log.debug("Произошла ошибка: Введенной брони не существует");
            throw new NotFoundAnythingException("Такой брони не существует");
        }
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean bookingExistById(UUID id) {
        for (Booking oldBooking : bookingRepository.findAll()) {
            if (Objects.equals(oldBooking.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
