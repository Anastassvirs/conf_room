package com.example.conf_room_sh.dto.booking;

import com.example.conf_room_sh.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getComment()
        );
    }

    public Page<BookingDto> toPageBookingDto(Page<Booking> bookingsPage) {
        return bookingsPage.map(this::toBookingDto);
    }
}
