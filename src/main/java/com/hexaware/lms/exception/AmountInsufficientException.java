package com.hexaware.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class AmountInsufficientException extends Exception{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;
    public AmountInsufficientException(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s is insufficient %s: %s",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}