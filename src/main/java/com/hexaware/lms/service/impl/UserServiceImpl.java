package com.hexaware.lms.service.impl;

import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.entity.User;
import com.hexaware.lms.exception.LoanLimitReachedException;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.repository.BookRepository;
import com.hexaware.lms.repository.UserRepository;
import com.hexaware.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hexaware.lms.utils.LoanStatus.LOAN;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @Override
    public Reservation createReservation(Long userid, Long bookid) throws ResourceNotFoundException {

        log.debug("Entered UserServiceImpl.createReservation()  with arg: {} and {} ",userid,bookid);
        Optional<User> user =  userRepository.findById(userid);
        Optional<Book> book = bookRepository.findById(bookid);

        //check if user and book id is present, isPresent()
        if(user.isEmpty())
        {
            throw new ResourceNotFoundException("user","userid",userid);
        }
        if(book.isEmpty())
        {
            throw new ResourceNotFoundException("book","bookid",bookid);
        }


        Reservation createdReservation = Reservation.builder()
                .user(user.get())
                .book(book.get())
                .build();
        log.debug("Exited UserServiceImpl.createReservation()  with return data: {} ",createdReservation.toString());
        return createdReservation;
    }

    @Override
    public Loan createLoan(Long userid, Long bookid) throws ResourceNotFoundException, LoanLimitReachedException {
        log.debug("Entered UserServiceImpl.createLoan()  with arg: {} and {} ",userid,bookid);
        Optional<User> user =  userRepository.findById(userid);
        Optional<Book> book = bookRepository.findById(bookid);

        //check if user and book id is present, isPresent()
        if(user.isEmpty())
        {
            throw new ResourceNotFoundException("user","userid",userid);
        }
        if(book.isEmpty())
        {
            throw new ResourceNotFoundException("book","bookid",bookid);
        }

        //check if 5 books have been loaned already
        int loanedbooks = userRepository.findById(userid).get().getNoOfBooksLoan();
        Loan createdLoan = Loan.builder()
                .user(user.get())
                .book(book.get())
                .returnDate(null)
                .status(LOAN)
                .build();
        if(loanedbooks==5)
        {
            throw new LoanLimitReachedException("Cannot Loan more than 5 books at a time.");
        }
        log.debug("Exited UserServiceImpl.Loan()  with return data: {} ",createdLoan.toString());
        return createdLoan;
    }
}
