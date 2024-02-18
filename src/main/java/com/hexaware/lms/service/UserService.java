package com.hexaware.lms.service;

import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;

public interface UserService {
    public Reservation createReservation(Long userid, Long bookid);

    public Loan createLoan(Long userid, Long bookid);
}
