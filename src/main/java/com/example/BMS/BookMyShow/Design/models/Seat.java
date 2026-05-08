package com.example.BMS.BookMyShow.Design.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{

    private String seatNumber;

    private Integer row;

    private Integer column;

    private SeatType seatType;

    @ManyToOne
    private Auditorium auditorium;

}
