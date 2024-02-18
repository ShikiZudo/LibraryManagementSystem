package com.hexaware.lms.service.impl;

import com.hexaware.lms.Mapper.impl.CategoryMapper;
import com.hexaware.lms.Mapper.impl.NotificationMapper;
import com.hexaware.lms.dto.*;
import com.hexaware.lms.entity.*;
import com.hexaware.lms.exception.ResourceNotFoundException;
import com.hexaware.lms.repository.*;
import com.hexaware.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final NotificationRepository notificationRepository;
    private final CategoryMapper categoryMapper;
    private final NotificationMapper notificationMapper;
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> resultEntity = categoryRepository.findAll();
        List<CategoryDTO> resultDto = resultEntity.stream().map(it-> categoryMapper.mapTo(it)).collect(Collectors.toList());
        return resultDto;
    }

    @Override
    public CategoryDTO addCategory(AddCategoryRequestBody request) {
        Category newCategory = new Category(request.getCategory());
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.mapTo(savedCategory);
    }

    @Override
    public void deleteCategory(long id) throws ResourceNotFoundException {
        if(!categoryRepository.existsById(id)) throw new ResourceNotFoundException("bookId","id",id);
        categoryRepository.deleteById(id);
    }

    @Override
    public NotificationDTO sendReturnRequest(NotificationDTO notificationDTO, long id) throws ResourceNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new ResourceNotFoundException("userId","id",notificationDTO.getId());
        notificationDTO.setUser(user.get());

        Notification notificationEntity = notificationMapper.mapFrom(notificationDTO);

        Notification savedEntity = notificationRepository.save(notificationEntity);
        return notificationMapper.mapTo(savedEntity);
    }

    @Override
    public List<BookLoanHistoryDTO> getBookLoanHistory(long bookId) throws ResourceNotFoundException {

        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()) throw new ResourceNotFoundException("book","id",bookId);


        List<Loan> loanList = loanRepository.findAllByBook(book.get());
        log.info("loan list found");

        List<BookLoanHistoryDTO> BookLoanHistoryList = loanList.stream().map(it-> {
            User user = it.getUser();
            String userName="";
            if(user ==null) {
                log.info("user not found");
            }else {
                userName = user.getFirstName()+user.getLastName();
            }

            return BookLoanHistoryDTO.builder()
                    .user(userName)
                    .id(it.getId())
                    .issueDate(it.getIssueDate())
                    .returnDate(it.getReturnDate())
                    .status(it.getStatus())
            .build();
        }).collect(Collectors.toList());
        log.info("list created");
        return BookLoanHistoryList;
    }

    @Override
    public List<BookReservationHistoryDTO> getBookReservationHistory(long bookId) throws ResourceNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()) throw new ResourceNotFoundException("book","id",bookId);


        List<Reservation> reserveList = reservationRepository.findAllByBook(book.get());
        log.info("loan list found");

        List<BookReservationHistoryDTO> BookReservationHistoryList = reserveList.stream().map(it-> {
            User user = it.getUser();
            String userName="";
            if(user ==null) {
                log.info("user not found");
            }else {
                userName = user.getFirstName()+user.getLastName();
            }

            return BookReservationHistoryDTO.builder()
                    .user(userName)
                    .id(it.getId())
                    .issueTimestamp(it.getIssueTimestamp())
                    .build();
        }).collect(Collectors.toList());
        log.info("list created");
        return BookReservationHistoryList;
    }

    @Override
    public List<UserLoanHistoryDTO> getUserLoanHistory(long userId) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new ResourceNotFoundException("user","id",userId);


        List<Loan> loanList = loanRepository.findAllByUser(user.get());
        log.info("loan list found");

        List<UserLoanHistoryDTO> UserLoanHistoryList = loanList.stream().map(it-> {

            Book book = it.getBook();
            String bookName="";
            if(book ==null) {
                log.info("user not found");
            }else {
                bookName = book.getTitle();
            }

            return UserLoanHistoryDTO.builder()
                    .book(bookName)
                    .id(it.getId())
                    .issueDate(it.getIssueDate())
                    .returnDate(it.getReturnDate())
                    .status(it.getStatus())
                    .build();
        }).collect(Collectors.toList());
        log.info("list created");
        return UserLoanHistoryList;
    }

    @Override
    public List<fineDTO> getUserFine(long userId) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new ResourceNotFoundException("user","id",userId);

        List<Loan> loanList = loanRepository.findAllByUser(user.get());
        log.info("loan list found");

        List<fineDTO> userFineList = loanList.stream().map(it->{

            Book book = it.getBook();
            String bookName = book.getTitle();
//            if(book ==null) {
//                log.info("user not found");
//            }else {
//                bookName = book.getTitle();
//            }

            User finedUser = it.getUser();
            String userName = finedUser.getFirstName()+finedUser.getLastName();
//            if(finedUser ==null) {
//                log.info("user not found");
//            }else {
//                userName = finedUser.getFirstName()+finedUser.getLastName();
//            }

            Duration duration = Duration.between(it.getIssueDate(),it.getReturnDate());
            Long fine = (duration.toDays() <=7) ? 0 : (duration.toDays()-7) * 5;

            return fineDTO.builder()
                    .book(bookName)
                    .user(userName)
                    .id(it.getId())
                    .issueDate(it.getIssueDate())
                    .returnDate(it.getReturnDate())
                    .status(it.getStatus())
                    .fineAmount(fine)
                    .build();
        }).collect(Collectors.toList());

        return userFineList;
    }

    @Override
    public List<fineDTO> getTotalFine() throws ResourceNotFoundException {
        List<Loan> loanList = loanRepository.overdueLoans();

        List<fineDTO> totalFineList = loanList.stream().map(it->{

            Book book = it.getBook();
            String bookName = book.getTitle();
            User finedUser = it.getUser();
            String userName = finedUser.getFirstName()+finedUser.getLastName();

            Duration duration = Duration.between(it.getIssueDate(),it.getReturnDate());
            Long fine = (duration.toDays() <=7) ? 0 : (duration.toDays()-7) * 5;

            return fineDTO.builder()
                    .book(bookName)
                    .user(userName)
                    .id(it.getId())
                    .issueDate(it.getIssueDate())
                    .returnDate(it.getReturnDate())
                    .status(it.getStatus())
                    .fineAmount(fine)
                    .build();
        }).collect(Collectors.toList());
        return totalFineList;
    }
}