package com.hexaware.lms.service.impl;

import com.hexaware.lms.Mapper.Mapper;
import com.hexaware.lms.dto.BookDto;
import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Category;
import com.hexaware.lms.repository.BookRepository;
import com.hexaware.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    private final Mapper<Book, BookDto> bookMapper;




    //create/add book
    //full update book
    @Override
    public BookDto save(BookDto bookDto) {
        log.info("Inside save Book mehtod");
        Book book = bookMapper.mapFrom(bookDto);
        BookDto bookDto1 = bookMapper.mapTo(bookRepository.save(book));

        return bookDto1;
    }

    // read/get all books
    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        //map to optional

        List<BookDto> bookDtos = books.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
        return bookDtos;
    }

    // read/get by id
    @Override
    public BookDto findOne(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        BookDto bookDto =null;
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookDto = bookMapper.mapTo(book);
        } else {
            // Handle the case where the Optional<Book> is empty
            //add logger and throw exception of resource not found
        }
        return bookDto;
    }

    //check if book exists
    @Override
    public boolean isExists(Long id) {
        return bookRepository.existsById(id);
    }

    // partial book update
    @Override
    public BookDto partialUpdate(Long id, BookDto bookDto) {

        Book bookEntity = bookMapper.mapFrom(bookDto);

        return bookRepository.findById(id).map(existingBook -> {
            // Update existingBook with data from bookEntity
            Optional.ofNullable(bookEntity.getCost()).ifPresent(existingBook::setCost);
            Optional.ofNullable(bookEntity.getEdition()).ifPresent(existingBook::setEdition);
            Optional.ofNullable(bookEntity.getDescription()).ifPresent(existingBook::setDescription);
            Optional.ofNullable(bookEntity.getIsbn()).ifPresent(existingBook::setIsbn);
            Optional.ofNullable(bookEntity.getAuthorName()).ifPresent(existingBook::setAuthorName);
            Optional.ofNullable(bookEntity.getLanguage()).ifPresent(existingBook::setLanguage);
            Optional.ofNullable(bookEntity.getPublisherName()).ifPresent(existingBook::setPublisherName);
            Optional.ofNullable(bookEntity.getImageURL()).ifPresent(existingBook::setImageURL);
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(bookEntity.getPages()).ifPresent(existingBook::setPages);
            Optional.ofNullable(bookEntity.getBookCount()).ifPresent(existingBook::setBookCount);

            // Save the updated bookEntity
            Book updatedBook = bookRepository.save(existingBook);

            // Convert the updated BookEntity back to a BookDto
            return bookMapper.mapTo(updatedBook);
        }).orElse(null);

    }

    //delete by id
    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    //search bar autosuggestions
    @Override
    public List<Book> searchBarBook(String search) {
        //map to book and bookdto
        return bookRepository.searchBook(search);
    }

    @Override
    public List<Optional<Book>> searchByAuthor(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    @Override
    public List<Optional<Book>> searchByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    @Override
    public List<Book> findByCategory(String search) {
        return bookRepository.findByCategory(search);
    }


}
