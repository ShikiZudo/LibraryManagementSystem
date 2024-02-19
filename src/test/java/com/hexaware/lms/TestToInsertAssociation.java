package com.hexaware.lms;


import com.hexaware.lms.entity.BookCategoryMapper;
import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import com.hexaware.lms.repository.*;
import com.hexaware.lms.utils.LoanStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@Slf4j
public class TestToInsertAssociation {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCategoryMapperRepository bookCategoryMapperRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
//    @Disabled
    public void insertLoanTest(){
        Loan loan1 = Loan.builder()
                .book(bookRepository.findById(1L).get())
                .user(userRepository.findById(3L).get())
                .status(LoanStatus.LOAN)
                .issueDate(OffsetDateTime.now().minus(5, ChronoUnit.DAYS))
                .returnDate(null)
                .build();
        loanRepository.save(loan1);
        //
        Loan loan2 = Loan.builder()
                .book(bookRepository.findById(2L).get())
                .user(userRepository.findById(3L).get())
                .status(LoanStatus.LOST)
                .issueDate(OffsetDateTime.now().minus(25,ChronoUnit.DAYS))
                .returnDate(OffsetDateTime.now().minus(10, ChronoUnit.DAYS))
                .build();
        loanRepository.save(loan2);
        //
        Loan loan3 = Loan.builder()
                .book(bookRepository.findById(3L).get())
                .user(userRepository.findById(3L).get())
                .status(LoanStatus.RETURNED)
                .issueDate(OffsetDateTime.now().minus(25,ChronoUnit.DAYS))
                .returnDate(OffsetDateTime.now().minus(15, ChronoUnit.DAYS))
                .build();
        loanRepository.save(loan3);
        //
        Loan loan4 = Loan.builder()
                .book(bookRepository.findById(4L).get())
                .user(userRepository.findById(4L).get())
                .status(LoanStatus.RETURNED)
                .issueDate(OffsetDateTime.now().minus(28,ChronoUnit.DAYS))
                .returnDate(OffsetDateTime.now().minus(22, ChronoUnit.DAYS))
                .build();
        loanRepository.save(loan4);
        //
        Loan loan5 = Loan.builder()
                .book(bookRepository.findById(5L).get())
                .user(userRepository.findById(4L).get())
                .status(LoanStatus.LOAN)
                .issueDate(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .returnDate(null)
                .build();
        loanRepository.save(loan5);
        //
        Loan loan6 = Loan.builder()
                .book(bookRepository.findById(6L).get())
                .user(userRepository.findById(5L).get())
                .status(LoanStatus.RETURNED)
                .issueDate(OffsetDateTime.now().minus(25,ChronoUnit.DAYS))
                .returnDate(OffsetDateTime.now().minus(18, ChronoUnit.DAYS))
                .build();
        loanRepository.save(loan6);
        //
        Loan loan7 = Loan.builder()
                .book(bookRepository.findById(7L).get())
                .user(userRepository.findById(6L).get())
                .status(LoanStatus.LOAN)
                .issueDate(OffsetDateTime.now().minus(4,ChronoUnit.DAYS))
                .returnDate(null)
                .build();
        loanRepository.save(loan7);
    }

    @Test
//    @Disabled
    public void insertReservationTests(){
        Reservation reservation1 = Reservation.builder()
                .book(bookRepository.findById(8L).get())
                .user(userRepository.findById(3L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation1);

        Reservation reservation2 = Reservation.builder()
                .book(bookRepository.findById(9L).get())
                .user(userRepository.findById(4L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation2);

        Reservation reservation3 = Reservation.builder()
                .book(bookRepository.findById(10L).get())
                .user(userRepository.findById(5L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation3);

        Reservation reservation4 = Reservation.builder()
                .book(bookRepository.findById(6L).get())
                .user(userRepository.findById(5L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation4);

        Reservation reservation5 = Reservation.builder()
                .book(bookRepository.findById(11L).get())
                .user(userRepository.findById(6L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation5);

        Reservation reservation6 = Reservation.builder()
                .book(bookRepository.findById(9L).get())
                .user(userRepository.findById(7L).get())
                .issueTimestamp(OffsetDateTime.now().minus(5,ChronoUnit.DAYS))
                .build();
        reservationRepository.save(reservation6);
    }

    @Test
//    @Disabled
    public void insertBookCategoryMappingTest(){

        BookCategoryMapper bookCategoryMapper1 = BookCategoryMapper.builder()
                .book(bookRepository.findById(1L).get())
                .category(categoryRepository.findById(1L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper1);
//
        BookCategoryMapper bookCategoryMapper2 = BookCategoryMapper.builder()
                .book(bookRepository.findById(2L).get())
                .category(categoryRepository.findById(2L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper2);
//
        BookCategoryMapper bookCategoryMapper3 = BookCategoryMapper.builder()
                .book(bookRepository.findById(4L).get())
                .category(categoryRepository.findById(7L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper3);
//
        BookCategoryMapper bookCategoryMapper4 = BookCategoryMapper.builder()
                .book(bookRepository.findById(5L).get())
                .category(categoryRepository.findById(4L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper4);
//
        BookCategoryMapper bookCategoryMapper5 = BookCategoryMapper.builder()
                .book(bookRepository.findById(6L).get())
                .category(categoryRepository.findById(1L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper5);
//
        BookCategoryMapper bookCategoryMapper6 = BookCategoryMapper.builder()
                .book(bookRepository.findById(7L).get())
                .category(categoryRepository.findById(8L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper6);
//
        BookCategoryMapper bookCategoryMapper7 = BookCategoryMapper.builder()
                .book(bookRepository.findById(8L).get())
                .category(categoryRepository.findById(4L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper7);
//
        BookCategoryMapper bookCategoryMapper8 = BookCategoryMapper.builder()
                .book(bookRepository.findById(10L).get())
                .category(categoryRepository.findById(9L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper8);
//
        BookCategoryMapper bookCategoryMapper9 = BookCategoryMapper.builder()
                .book(bookRepository.findById(11L).get())
                .category(categoryRepository.findById(1L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper9);
//
        BookCategoryMapper bookCategoryMapper10 = BookCategoryMapper.builder()
                .book(bookRepository.findById(11L).get())
                .category(categoryRepository.findById(10L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper10);
//
        BookCategoryMapper bookCategoryMapper11 = BookCategoryMapper.builder()
                .book(bookRepository.findById(3L).get())
                .category(categoryRepository.findById(6L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper11);
//
        BookCategoryMapper bookCategoryMapper12 = BookCategoryMapper.builder()
                .book(bookRepository.findById(3L).get())
                .category(categoryRepository.findById(8L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper12);
//
        BookCategoryMapper bookCategoryMapper13 = BookCategoryMapper.builder()
                .book(bookRepository.findById(9L).get())
                .category(categoryRepository.findById(6L).get())
                .build();
        bookCategoryMapperRepository.save(bookCategoryMapper13);
    }
}
