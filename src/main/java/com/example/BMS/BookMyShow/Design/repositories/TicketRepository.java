package com.example.BMS.BookMyShow.Design.repositories;

import com.example.BMS.BookMyShow.Design.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
