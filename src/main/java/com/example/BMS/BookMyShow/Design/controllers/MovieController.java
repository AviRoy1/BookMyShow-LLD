package com.example.BMS.BookMyShow.Design.controllers;

import com.example.BMS.BookMyShow.Design.models.Movie;
import com.example.BMS.BookMyShow.Design.services.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable long id) {
        return movieService.findMovieById(id);
    }
}
