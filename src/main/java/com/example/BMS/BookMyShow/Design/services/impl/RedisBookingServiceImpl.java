package com.example.BMS.BookMyShow.Design.services.impl;

import com.example.BMS.BookMyShow.Design.models.ShowSeat;
import com.example.BMS.BookMyShow.Design.models.ShowSeatStatus;
import com.example.BMS.BookMyShow.Design.models.Ticket;
import com.example.BMS.BookMyShow.Design.repositories.ShowSeatRepository;
import com.example.BMS.BookMyShow.Design.services.IBookingService;
import com.example.BMS.BookMyShow.Design.services.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisBookingServiceImpl implements IBookingService {

    private final ICacheService cacheService;
    private final ShowSeatRepository showSeatRepository;

    public RedisBookingServiceImpl(ShowSeatRepository showSeatRepository) {
        this.cacheService = new RedisServiceImpl();
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {

        // 1. Check if the seats are available or not.

        // a. Check if the seats are not booked already.
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatIdAndShowId(seatIds, showId);
        for(ShowSeat seat: showSeats) {
            if(seat.getStatus().equals(ShowSeatStatus.BOOKED)) {
                return false;
            }
        }

        // b. Check if the seats are not locked already.
        for(ShowSeat seat: showSeats) {
            String status = cacheService.get("seatId-"+seat.getId()+"-userId-"+userId).toString();
            if(status != null)
                return false;
        }

        // 2. If all the seat are available then block the seat in redis - seatId - userId
        for(ShowSeat seat: showSeats) {
            cacheService.set("seatId-"+seat.getId()+"-userId-"+userId, "LOCKED");
        }

        System.out.println("Printing cache after logic");
        cacheService.getAllKeysAndValues();
        return false;
    }

    @Override
    public Optional<Ticket> bookTicket(long showId, List<Long> seatIds, long userId) {
        return Optional.empty();
    }
}

