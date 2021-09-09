package com.datamanagement.dto;

import com.datamanagement.booking.Booking;

public class BookingDto {
    private String uuid;
    private Booking booking;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
