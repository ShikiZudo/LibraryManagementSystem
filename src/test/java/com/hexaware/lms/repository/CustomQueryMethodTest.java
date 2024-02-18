package com.hexaware.lms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomQueryMethodTest {
    @Autowired  //field injection
    private BookRepository bookRepository;

    @Test
    void searchBarBookTest(){
        bookRepository.searchBook("Harry");
    }
}
