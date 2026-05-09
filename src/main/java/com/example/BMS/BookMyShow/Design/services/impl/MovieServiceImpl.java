package com.example.BMS.BookMyShow.Design.services.impl;

import com.example.BMS.BookMyShow.Design.models.Movie;
import com.example.BMS.BookMyShow.Design.repositories.MovieRepository;
import com.example.BMS.BookMyShow.Design.services.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("No Movie found with the id: "+id));
    }
}
