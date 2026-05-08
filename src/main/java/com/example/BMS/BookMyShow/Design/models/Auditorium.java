package com.example.BMS.BookMyShow.Design.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {

    private String name;
    private Integer capacity;

    @ManyToOne
    private Theatre theatre;

    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seats;

    @OneToMany(mappedBy = "auditorium")
    private List<Show> shows;
}
