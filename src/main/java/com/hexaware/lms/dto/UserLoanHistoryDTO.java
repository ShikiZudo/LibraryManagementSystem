package com.hexaware.lms.dto;

import com.hexaware.lms.utils.LoanStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoanHistoryDTO {

    private Long id;
    private OffsetDateTime issueDate;
    private OffsetDateTime returnDate;
    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;
    private String book;
}
