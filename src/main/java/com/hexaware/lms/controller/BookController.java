package com.hexaware.lms.controller;

import com.hexaware.lms.dto.BookDto;
import com.hexaware.lms.entity.Book;
import com.hexaware.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;


    //create book

    @PostMapping(path= "/addbook")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto)
    {
        log.info("Inside book controller addbook");
        BookDto savedBookDto = bookService.save(bookDto);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED) ;
    }

    //get all books
    @GetMapping(path="/books")
    public List<BookDto> getAllBooks(){
        return bookService.findAll();
    }

    //get book by id
    @GetMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id){

        if(!bookService.isExists(id))   // if book does not exist
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findOne(id), HttpStatus.OK);    // if book exists
    }

    //book full update
    @PutMapping(path = "/books/{id}")
    public ResponseEntity<BookDto> fullUpdateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto){

        if(!bookService.isExists(id))    //book doesnt exist
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.save(bookDto),HttpStatus.OK);
    }

    //book partial update
    @PatchMapping(path="/books/{id}")
    public ResponseEntity<BookDto> partialUpdate(@PathVariable("id") Long id, @RequestBody BookDto bookDto){
        if(!bookService.isExists(id))    //book doesnt exist
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookDto updatedBookDto = bookService.partialUpdate(id, bookDto);
        return new ResponseEntity<>(updatedBookDto,HttpStatus.OK);
    }

    //delete by id
    @DeleteMapping(path = "/book/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id){
        bookService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //search bar autosuggestions
    @GetMapping(path = "/search/{searchquery}")
    public List<Book> getBookSearchBar(@PathVariable("searchquery") String search){

           return bookService.searchBarBook(search);
    }

    //find by authorname
    @GetMapping(path = "/search/authorname/{search}")
    public List<Optional<Book>> getBookByAuthor(@PathVariable("search") String search)
    {
        return bookService.searchByAuthor(search);
    }

    //find by language
    @GetMapping(path = "/search/language/{search}")
    public List<Optional<Book>> getBookByLanguage(@PathVariable("search") String search)
    {
        return bookService.searchByLanguage(search);
    }

    //find by category
    @GetMapping(path = "/search/category/{search}")
    public List<Book> getBookByCategory(@PathVariable("search") String search)
    {
        return bookService.findByCategory(search);
    }


}
