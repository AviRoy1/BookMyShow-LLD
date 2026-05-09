package com.example.BMS.BookMyShow.Design.repositories;

import com.example.BMS.BookMyShow.Design.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
