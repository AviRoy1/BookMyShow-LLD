package com.example.BMS.BookMyShow.Design.controllers;

import com.example.BMS.BookMyShow.Design.dto.BlockSeatsRequestDto;
import com.example.BMS.BookMyShow.Design.services.IBookingService;
import com.example.BMS.BookMyShow.Design.services.impl.RedisBookingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public boolean blockSeats(@RequestBody BlockSeatsRequestDto requestDto) {
        return bookingService.blockSeats(requestDto.getShowId(), requestDto.getSeatIds(), requestDto.getUserId());
    }

}
