package com.example.conf_room_sh.service.booking;

import com.example.conf_room_sh.dto.booking.BookingDto;
import com.example.conf_room_sh.dto.booking.BookingMapper;
import com.example.conf_room_sh.entity.Booking;
import com.example.conf_room_sh.entity.TimeSlot;
import com.example.conf_room_sh.entity.User;
import com.example.conf_room_sh.exception.NotFoundAnythingException;
import com.example.conf_room_sh.repository.BookingRepository;
import com.example.conf_room_sh.repository.TimeSlotRepository;
import com.example.conf_room_sh.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, TimeSlotRepository timeSlotRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
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
    public BookingDto create(LocalDateTime start, LocalDateTime end, UUID room_id, List<String> guestEmails, String comment) {
        Set<User> guests = guestEmails.stream()
                .map(email -> userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Пользователь с данным email-ом не найден: " + email)))
                .collect(Collectors.toSet());


        Set<TimeSlot> timeSlots = timeSlotRepository.findAllWithinRange(start, end);
        boolean allAvailable = timeSlots.stream().allMatch(TimeSlot::getAvaliable);
        if (!allAvailable) {
            throw new RuntimeException("Один или несколько слотов уже забронированы");
        }

        Booking booking = new Booking();
        booking.setComment(comment);
        booking.setUsers(guests);

        timeSlots.forEach(timeSlot -> {
            timeSlot.setAvaliable(false);
            timeSlot.setBooking(booking);
        });

        return bookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Transactional
    @Override
    public BookingDto update(UUID id, BookingDto bookingDto) {
        Booking booking = getById(id);
        Optional.ofNullable(bookingDto.getComment()).ifPresent(booking::setComment);
        if (bookingRepository.existsById(id)) {
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

}
