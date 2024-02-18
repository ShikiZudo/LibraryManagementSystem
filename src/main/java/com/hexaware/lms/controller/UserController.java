package com.hexaware.lms.controller;

import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.service.BookService;
import com.hexaware.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
//@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    //create new reservation
    @PostMapping(path = "/reservation/{userid}/{bookid}")
    public Reservation createReservation(@PathVariable("userid") Long userid, @PathVariable("bookid") Long bookid){
        return userService.createReservation(userid,bookid);
    }

    //create new book loan
    @PostMapping(path = "/loan/{userid}/{bookid}")
    public Loan createLoan(@PathVariable("userid") Long userid, @PathVariable("bookid") Long bookid){
        return userService.createLoan(userid,bookid);
    }
}