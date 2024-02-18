package com.hexaware.lms.service.impl;

import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.entity.User;
import com.hexaware.lms.repository.BookRepository;
import com.hexaware.lms.repository.UserRepository;
import com.hexaware.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hexaware.lms.utils.LoanStatus.LOAN;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    @Override
    public Reservation createReservation(Long userid, Long bookid) {
        Optional<User> user =  userRepository.findById(userid);
        Optional<Book> book = bookRepository.findById(bookid);

        //check if user and book id is present, isPresent()

        return Reservation.builder()
                .user(user.get())
                .book(book.get())
                .build();
    }

    @Override
    public Loan createLoan(Long userid, Long bookid) {
        Optional<User> user =  userRepository.findById(userid);
        Optional<Book> book = bookRepository.findById(bookid);

        //check if user and book id is present, isPresent()

        return Loan.builder()
                .user(user.get())
                .book(book.get())
                .returnDate(null)
                .status(LOAN)
                .build();
    }
}
