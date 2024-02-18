package com.hexaware.lms.service;

import com.hexaware.lms.dto.BookDto;
import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Category;


import java.util.List;
import java.util.Optional;

public interface BookService {
    //create / full update book
    BookDto save(BookDto bookDto);

    List<BookDto> findAll();

     BookDto findOne(Long id);

    boolean isExists(Long id);

    BookDto partialUpdate(Long id, BookDto bookEntity);

    void delete(Long id);
    List<Book> searchBarBook( String search);

    List<Optional<Book>> searchByAuthor(String authorName);

    List<Optional<Book>> searchByLanguage(String language);

    List<Book> findByCategory(String search);

}
