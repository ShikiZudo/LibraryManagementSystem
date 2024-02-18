package com.hexaware.lms.service;

import com.hexaware.lms.dto.UserLoanHistoryDTO;
import com.hexaware.lms.dto.fineDTO;
import com.hexaware.lms.dto.SubmitBookDTO;
import com.hexaware.lms.exception.AmountInsufficientException;
import com.hexaware.lms.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    List<UserLoanHistoryDTO> getUserLoanHistory(long userId) throws ResourceNotFoundException;

    List<fineDTO> getUserFine(long userId) throws ResourceNotFoundException;

    void setStatusLost(long loanId) throws ResourceNotFoundException,IllegalArgumentException;

    SubmitBookDTO submitBook(long loanId, long fineAmount) throws ResourceNotFoundException, AmountInsufficientException,IllegalArgumentException;
}
