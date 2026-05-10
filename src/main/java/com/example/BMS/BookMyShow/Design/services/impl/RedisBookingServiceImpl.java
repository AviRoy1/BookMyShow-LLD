package com.example.BMS.BookMyShow.Design.services.impl;

import com.example.BMS.BookMyShow.Design.models.*;
import com.example.BMS.BookMyShow.Design.repositories.ShowRepository;
import com.example.BMS.BookMyShow.Design.repositories.ShowSeatRepository;
import com.example.BMS.BookMyShow.Design.repositories.TicketRepository;
import com.example.BMS.BookMyShow.Design.repositories.UserRepository;
import com.example.BMS.BookMyShow.Design.services.IBookingService;
import com.example.BMS.BookMyShow.Design.services.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RedisBookingServiceImpl implements IBookingService {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {
        System.out.println("Printing cache before logic");
        cacheService.getAllKeysAndValues();

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
            String status = (String) cacheService.get("seatId-"+seat.getId()+"-userId-"+userId);
            if(status != null)
                return false;
        }

        // 2. If all the seat are available then block the seat in redis - seatId - userId
        for(ShowSeat seat: showSeats) {
            cacheService.set("seatId-"+seat.getId()+"-userId-"+userId, "LOCKED");
        }

        System.out.println("Printing cache after logic");
        cacheService.getAllKeysAndValues();
        return true;
    }

    @Transactional
    @Override
    public Optional<Ticket> bookTicket(long showId, List<Long> seatIds, long userId) {

        //  1. In redis check if the user has lock all the seats that they are trying to book
        for(long seatId: seatIds) {
            String status = (String) cacheService.get("seatId-"+seatId+"-userId-"+userId);
            if(null == status){
                return Optional.empty();
            }
        }

        // 2. Create ticket
        Ticket ticket = createTicket(showId, seatIds, userId);

        // 3. Update show_seat status for all the seats and update ticket id.
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatIdAndShowId(seatIds, showId);
        for(ShowSeat seat: showSeats) {
            seat.setStatus(ShowSeatStatus.BOOKED);
            seat.setTicket(ticket);
        }
        showSeatRepository.saveAll(showSeats);


        return Optional.of(ticket);
    }

    @Override
    public boolean clearAllSeatLocks() {
        cacheService.deleteAll();
        return true;
    }

    private Ticket createTicket(long showId, List<Long> seatIds, long userId) {
        Ticket ticket = new Ticket();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        Show show = showRepository.findById(showId).orElseThrow(() -> new RuntimeException("Show not found."));
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setAmount(BigDecimal.valueOf(2000));
        ticket.setStatus(TicketStatus.BOOKED);
        ticket = ticketRepository.save(ticket);
        return ticket;
    }
}

