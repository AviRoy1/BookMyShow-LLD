package com.example.BMS.BookMyShow.Design.services;

import com.example.BMS.BookMyShow.Design.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovieService {

    public List<Movie> findAllMovies();

    public Movie findMovieById(long id);

}
