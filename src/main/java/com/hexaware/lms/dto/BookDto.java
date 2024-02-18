package com.hexaware.lms.dto;

import com.hexaware.lms.entity.Category;
import com.hexaware.lms.entity.Loan;
import com.hexaware.lms.entity.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@Builder
public class BookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;
    private String title;
    private String isbn;
    private String authorName;
    private String publisherName;//
    private String edition;//
    private String description;
    private String language;//
    private int pages;
    private BigDecimal cost;//
    private int bookCount;
    private String imageURL;

    //private List<Category> categoryList ;

}

