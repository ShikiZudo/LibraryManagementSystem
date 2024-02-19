package com.hexaware.lms.entity;

import com.hexaware.lms.utils.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_seq")
    private Long id;

    @CreationTimestamp
    private OffsetDateTime issueDate;

    private OffsetDateTime returnDate;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

}

