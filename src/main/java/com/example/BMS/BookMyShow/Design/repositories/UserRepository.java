package com.example.BMS.BookMyShow.Design.repositories;

import com.example.BMS.BookMyShow.Design.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
