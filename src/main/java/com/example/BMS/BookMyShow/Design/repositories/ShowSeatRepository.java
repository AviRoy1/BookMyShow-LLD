package com.example.BMS.BookMyShow.Design.repositories;

import com.example.BMS.BookMyShow.Design.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllByShowId(long showId);

}
