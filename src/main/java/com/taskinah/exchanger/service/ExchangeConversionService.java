package com.taskinah.exchanger.service;

import com.taskinah.exchanger.entity.Transaction;
import com.taskinah.exchanger.exception.customExceptions.ExchangeRateNotFound;
import com.taskinah.exchanger.model.ConversionRequest;
import com.taskinah.exchanger.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExchangeConversionService {

    private final ExchangeRateService exchangeRateService;

    private final TransactionRepository repository;

    public Transaction convert(ConversionRequest request) {

        var exchangeRate = exchangeRateService.getExchangeRate(request.getSourceCurrency(), request.getTargetCurrency());

        if (exchangeRate == null || exchangeRate < 0) {
            throw new ExchangeRateNotFound(String.format("Exchange rate not found for the symbols : %s, %s", request.getSourceCurrency(), request.getTargetCurrency()));
        }

        var s = new Transaction();
        s.setAmount(request.getAmount());
        s.setSourceCurrency(request.getSourceCurrency());
        s.setTargetCurrency(request.getTargetCurrency());
        s.setTransactionId(UUID.randomUUID().toString());
        s.setExchangeRate(exchangeRate);
        s.setTargetAmount(request.getAmount() / exchangeRate);
        return repository.save(s);
    }

    public Page<Transaction> getTransactionsByDate(Pageable pageable, Date from, Date to) {
        return repository.getTransactionByCreatedAtAfterAndCreatedAtBefore(pageable, from, to);
    }

    public Page<Transaction> getTransactionsByTransactionId(Pageable pageable, String transactionId) {
        return repository.getTransactionByTransactionId(pageable, transactionId);
    }
}
