package com.hexaware.lms.controller;

import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.exception.LoanLimitReachedException;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.service.BookService;
import com.hexaware.lms.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
//@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    //create new reservation
    @PostMapping(path = "/reservation/{userid}/{bookid}")
    public Reservation createReservation(@PathVariable("userid") @NotNull Long userid, @PathVariable("bookid") @NotNull Long bookid) throws ResourceNotFoundException {
        log.debug("Entered createReservation() controller.");
        log.info("Request recieved: api/v1/user/reservation/{userid}/{bookid}");
        Reservation createdReservation = userService.createReservation(userid,bookid);
        log.debug("Exited createReservation() controller.");
        return createdReservation;
    }

    //create new book loan
    @PostMapping(path = "/loan/{userid}/{bookid}")
    public Loan createLoan(@PathVariable("userid") @NotNull Long userid, @PathVariable("bookid") @NotNull Long bookid) throws ResourceNotFoundException, LoanLimitReachedException {
        log.debug("Entered createLoan() controller.");
        log.info("Request recieved: api/v1/user/loan/{userid}/{bookid}");
        Loan createdLoan = userService.createLoan(userid,bookid);
        log.debug("Exited createLoan() controller.");
        return createdLoan;
    }
}