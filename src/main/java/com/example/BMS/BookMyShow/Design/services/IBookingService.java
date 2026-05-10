package com.example.BMS.BookMyShow.Design.services;

import com.example.BMS.BookMyShow.Design.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBookingService {

    boolean blockSeats(long showId, List<Long> seatIds, long userId);

    Optional<Ticket> bookTicket(long showId, List<Long> seatIds, long userId);
}
