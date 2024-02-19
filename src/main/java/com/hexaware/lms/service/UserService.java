package com.hexaware.lms.service;

import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.exception.LoanLimitReachedException;
import com.hexaware.lms.exception.ResourceNotFoundException;

public interface UserService {
    public Reservation createReservation(Long userid, Long bookid) throws ResourceNotFoundException;

    public Loan createLoan(Long userid, Long bookid) throws ResourceNotFoundException, LoanLimitReachedException;
}
