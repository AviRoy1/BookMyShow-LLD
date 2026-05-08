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
public class Theatre extends BaseModel{

    private String name;
    private String address;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "theatre")
    List<Auditorium> auditoriums;

}
