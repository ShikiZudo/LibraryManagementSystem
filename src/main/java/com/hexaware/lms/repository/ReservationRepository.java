package com.hexaware.lms.repository;


import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByBook(Book book);
}
