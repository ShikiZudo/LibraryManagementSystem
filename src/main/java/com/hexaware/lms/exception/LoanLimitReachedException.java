package com.hexaware.lms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanLimitReachedException extends Exception{
    private String message;

    public LoanLimitReachedException(String message) {
        super(message);
    }
}
