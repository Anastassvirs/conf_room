package com.example.conf_room_sh.service.booking;

import com.example.conf_room_sh.dto.booking.BookingDto;
import com.example.conf_room_sh.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    Page<BookingDto> getAllPageable(Pageable pageable);

    BookingDto findById(UUID id);

    Booking getById(UUID id);

    BookingDto create(BookingDto bookingDto);

    BookingDto createBooking(LocalDateTime start, LocalDateTime end, UUID room_id, List<String> guestEmails, String comment);

    BookingDto update(UUID id, BookingDto bookingDto);

    void deleteById(UUID id);
}
