package com.chocolatestore.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<HttpStatus> exceptionNullPointer(NullPointerException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<HttpStatus> exceptionNullPointer(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<HttpStatus> exceptionNumberFormat(NumberFormatException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<HttpStatus> exceptionCustomerNotFound(CustomerNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ManufacturerNotFoundException.class)
    public ResponseEntity<HttpStatus> exceptionManufacturerNotFound(ManufacturerNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<HttpStatus> exceptionOrderNotFound(OrderNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<HttpStatus> exceptionProductNotFound(ProductNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HttpStatus> exceptionMissingServletRequestParameter(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<HttpStatus> exceptionJwt(JwtException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> exception(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}