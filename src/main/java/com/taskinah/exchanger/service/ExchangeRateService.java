package com.taskinah.exchanger.service;

import com.taskinah.exchanger.client.RatesApiClient;
import com.taskinah.exchanger.exception.customExceptions.ExchangeRateNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeRateService {

    private final RatesApiClient ratesApiClient;

    public Double getExchangeRate(String symbols, String base) {
        var exchangeRate = ratesApiClient.getExchangeRate(symbols, base);
        if (exchangeRate == null || exchangeRate <= 0) {
            throw new ExchangeRateNotFound(String.format("Exchange rate not found for the symbols : %s, %s", symbols, base));
        }
        return exchangeRate;
    }
}
