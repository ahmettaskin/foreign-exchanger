package com.taskinah.exchanger.exception.customExceptions;

public class ExchangeRateNotFound extends RuntimeException {
    public ExchangeRateNotFound(String message) {
        super(message);
    }
}
