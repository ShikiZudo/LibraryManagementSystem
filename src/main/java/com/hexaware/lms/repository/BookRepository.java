package com.hexaware.lms.repository;

import com.hexaware.lms.dto.BookDto;
import com.hexaware.lms.entity.Book;
import com.hexaware.lms.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //search bar autosuggestions
    @Query("SELECT b FROM Book b "
            + "WHERE b.title LIKE CONCAT('%', :query, '%') "
            + "OR b.description LIKE CONCAT('%', :query, '%')")
    List<Book> searchBook(String query);

    //get book by author
    public Optional<List<Book>> findByAuthorName(String author);

    //get book by language
    public Optional<List<Book>> findByLanguage(String language);

    //get book by category
    //@Query("SELECT DISTINCT b FROM Book b JOIN b.categories c WHERE c.category = :searchCategory")
    //public List<Book> searchCategory(String searchCategory);
    //@Query("SELECT DISTINCT b FROM Book b JOIN b.categoryList c WHERE c.category = ?1")

    @Query("SELECT bm.book FROM BookCategoryMapper bm JOIN bm.category c WHERE c.category = ?1")
    Optional<List<Book>> findByCategory(String categoryName);

}
