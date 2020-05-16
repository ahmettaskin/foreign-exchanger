package com.taskinah.exchanger.exception;

import com.taskinah.exchanger.exception.customExceptions.ExchangeRateNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExchangeRateNotFound.class)
    protected ResponseEntity<Object> handleExchangeRateNotFound(RuntimeException ex, WebRequest request) {
        log.info("Exchange rate not found. Message: {}.", ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleAnyError(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Internal server error.";
        log.error("Exception occurred. Exception message: {}, exception stack trace: {}.", ex.getMessage(), ex.getStackTrace());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

