package com.example.BMS.BookMyShow.Design.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {

    private String name;

    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    private Theatre theatre;

    @OneToMany(mappedBy = "auditorium", fetch = FetchType.LAZY)
    private List<Seat> seats;

    @OneToMany(mappedBy = "auditorium", fetch = FetchType.LAZY)
    private List<Show> shows;
}