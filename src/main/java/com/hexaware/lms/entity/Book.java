package com.hexaware.lms.entity;

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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Long id;
    private String title;
    private String isbn;
    private String authorName;
    private String publisherName;
    private String edition;
    private String description;
    private String language;
    private int pages;
    private BigDecimal cost;
    private int bookCount;
    private String imageURL;

    //foreign key reservation constraint
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Reservation> reservation;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Loan> loan;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="book_category_mapping",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id"))
    private List<Category> categoryList ;


}