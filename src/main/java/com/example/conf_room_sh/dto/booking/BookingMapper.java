package com.example.conf_room_sh.dto.booking;


import com.example.conf_room_sh.entity.Booking;

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
}
