package com.example.BMS.BookMyShow.Design.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
public class Ticket extends BaseModel{

    private BigDecimal amount;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @ManyToOne
    private ShowSeat seat;

    private TicketStatus status;
}
