package com.sample.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.sample.demo.util.LocalDateAndTime;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerPage {

	// handle ResourceNotFound Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> toHandleResourceNotFound(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new LocalDateAndTime().getLocalDateTime(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	// handle All type of Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> toHandleAll(Exception exception, WebRequest request) {
		ErrorDetails error = new ErrorDetails(new LocalDateAndTime().getLocalDateTime(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// to handle custom validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> toHandleAllCustomValidations(MethodArgumentNotValidException exception) {
		ErrorDetails error = new ErrorDetails(new LocalDateAndTime().getLocalDateTime(), "Its a validation error",
				exception.getBindingResult().getFieldError().getField() + "--"
						+ exception.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}
