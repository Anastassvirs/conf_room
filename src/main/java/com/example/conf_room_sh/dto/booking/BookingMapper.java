package com.example.conf_room_sh.dto.booking;

import com.example.conf_room_sh.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public static BookingDto toBookingDto(Booking booking) {
        return new BookingDto(
                booking.getComment()
        );
    }

    public static Booking toBooking(BookingDto bookingDto) {
        return new Booking(
                bookingDto.getComment()
        );
    }

    public static Page<BookingDto> toPageBookingDto(Page<Booking> bookingsPage) {
        return bookingsPage.map(BookingMapper::toBookingDto);
    }
}
