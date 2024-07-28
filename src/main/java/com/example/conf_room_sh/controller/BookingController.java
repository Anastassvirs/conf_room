package com.example.conf_room_sh.controller;

import com.example.conf_room_sh.dto.booking.BookingDto;
import com.example.conf_room_sh.service.booking.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public Page<BookingDto> findAll(@PageableDefault Pageable pageable) {
        return bookingService.getAllPageable(pageable);
    }

    public BookingDto find(@PathVariable UUID id) {
        return bookingService.findById(id);
    }

    @PostMapping
    public BookingDto create(@RequestParam LocalDateTime start, // TODO: Подумать, надо ли делать тут конвертацию из стринга или норм так передавать
                                    @RequestParam LocalDateTime end,
                                    @RequestParam UUID room_id,
                                    @RequestParam List<String> guestEmails,
                                    @RequestParam (required = false, defaultValue = "") String comment) {
        return bookingService.create(start, end, room_id, guestEmails, comment);
    }

    @PatchMapping(path = "/{bookingId}")
    public BookingDto update(@PathVariable UUID bookingId, @RequestBody BookingDto bookingDto) {
        return bookingService.update(bookingId, bookingDto);
    }

    @DeleteMapping(path = "/{bookingId}")
    public void deleteById(@PathVariable UUID bookingId) {
        bookingService.deleteById(bookingId);
    }

}
