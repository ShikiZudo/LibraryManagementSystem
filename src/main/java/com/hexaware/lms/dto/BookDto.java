package com.hexaware.lms.dto;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

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
    @NotBlank
    private String title;
    @Size(min = 5)
    private String isbn;
    @NotBlank
    private String authorName;
    private String publisherName;
    private String edition;
    private String description;
    @NotBlank
    private String language;//
    @PositiveOrZero
    private int pages;
    @Positive
    private BigDecimal cost;//
    @Min(1)
    private int bookCount;
    @NotEmpty
    private String imageURL;
    //private List<Long> categoryIdList ;

}