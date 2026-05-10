package com.example.BMS.BookMyShow.Design.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {

    private String seatNumber;

    private Integer rowNumber;

    private Integer columnNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    @JsonIgnore
    private Auditorium auditorium;
}
