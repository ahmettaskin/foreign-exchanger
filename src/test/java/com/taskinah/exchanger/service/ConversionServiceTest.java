package com.taskinah.exchanger.service;

import com.taskinah.exchanger.entity.Transaction;
import com.taskinah.exchanger.exception.customExceptions.ExchangeRateNotFound;
import com.taskinah.exchanger.model.ConversionRequest;
import com.taskinah.exchanger.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceTest {

    @InjectMocks
    private ConversionService conversionService;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private TransactionRepository repository;

    @Test
    public void convert_shouldSaveTransactionToDb_whenInputsAreValid(){
        //given
        var request = new ConversionRequest();
        request.setSourceCurrency("TRY");
        request.setTargetCurrency("USD");
        request.setAmount(100.0);
        Mockito.when(exchangeRateService.getExchangeRate(request.getSourceCurrency(),request.getTargetCurrency())).thenReturn(6.95);

        //when
        conversionService.convert(request);

        //then
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Transaction.class));
        Mockito.verify(exchangeRateService, Mockito.times(1)).getExchangeRate("TRY", "USD");
    }

    @Test(expected = ExchangeRateNotFound.class)
    public void convert_shouldThrowExchangeRateNotFoundException_whenInputsAreValid(){
        //given
        var request = new ConversionRequest();
        request.setSourceCurrency("TRY");
        request.setTargetCurrency("USD");
        request.setAmount(100.0);
        Mockito.when(exchangeRateService.getExchangeRate(request.getSourceCurrency(),request.getTargetCurrency())).thenReturn(null);

        //when
        conversionService.convert(request);
    }
}