package com.example.BMS.BookMyShow.Design.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private Show show;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<ShowSeat> showSeats;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}