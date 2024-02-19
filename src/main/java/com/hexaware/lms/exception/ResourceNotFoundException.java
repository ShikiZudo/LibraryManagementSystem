package com.hexaware.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;
    private String fieldValueString;
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValueString));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValueString = fieldValueString;
    }
}
