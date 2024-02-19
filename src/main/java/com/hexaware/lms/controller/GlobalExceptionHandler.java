package com.hexaware.lms.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexaware.lms.exception.AmountInsufficientException;
import com.hexaware.lms.exception.ErrorDetails;
import com.hexaware.lms.exception.NameAlreadyExistsException;
import com.hexaware.lms.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest w){

		ErrorDetails e = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				w.getDescription(false),
				"OBJECT_NOT_FOUND");

		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AmountInsufficientException.class)
	public ResponseEntity<ErrorDetails> handleAmountInsufficientException(AmountInsufficientException ex, WebRequest w){
		ErrorDetails e = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				w.getDescription(false),
				"PAYMENT_INSUFFICIENT_EXCEPTION");

		return new ResponseEntity<>(e, HttpStatus.PAYMENT_REQUIRED);
	}

	@ExceptionHandler(NameAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleNameAlreadyExistsException(NameAlreadyExistsException ex, WebRequest w){

		ErrorDetails e = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				w.getDescription(false),
				"OBJECT_NAME_ALREADY_EXISTS");

		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request
	) {
		Map<String, String> errors = new HashMap<>();
		List<ObjectError>errList = ex.getBindingResult().getAllErrors();

		errList.forEach((err)->{
			String fieldName = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
}
