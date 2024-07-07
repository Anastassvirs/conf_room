package com.example.conf_room_sh.controller;

import com.example.conf_room_sh.dto.booking.BookingDto;
import com.example.conf_room_sh.dto.user.UserDto;
import com.example.conf_room_sh.service.booking.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public BookingDto create(@RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @PatchMapping(path = "/{bookingId}")
    public BookingDto update(@PathVariable UUID bookingId, @RequestBody BookingDto bookingDto) {
        return bookingService.updateBooking(bookingId, bookingDto);
    }

    @DeleteMapping(path = "/{bookingId}")
    public void deleteById(@PathVariable UUID bookingId) {
        bookingService.deleteById(bookingId);
    }

}
