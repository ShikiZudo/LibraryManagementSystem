package com.hexaware.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_category_mapper")
@Builder
public class BookCategoryMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_category_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}