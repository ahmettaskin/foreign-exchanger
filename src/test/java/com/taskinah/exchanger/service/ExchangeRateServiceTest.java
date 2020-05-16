package com.taskinah.exchanger.service;

import com.taskinah.exchanger.client.RatesApiClient;
import com.taskinah.exchanger.exception.customExceptions.ExchangeRateNotFound;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceTest {

    @InjectMocks
    ExchangeRateService exchangeRateService;

    @Mock
    RatesApiClient ratesApiClient;

    @Test
    public void getExchangeRate_shouldRetrieveExchangeRate_whenInputsAreValid() {
        //given
        var expectedValue = 6.95;
        Mockito.when(ratesApiClient.getExchangeRate("TRY", "USD")).thenReturn(expectedValue);

        //when
        var exchangeRate = exchangeRateService.getExchangeRate("TRY", "USD");

        //then
        Assertions.assertEquals(expectedValue, exchangeRate);
        Mockito.verify(ratesApiClient, Mockito.times(1)).getExchangeRate("TRY", "USD");
    }

    @Test(expected = ExchangeRateNotFound.class)
    public void getExchangeRate_shouldThrowExchangeRateNotFoundException_whenInputsAreValid() {
        //given
        Mockito.when(ratesApiClient.getExchangeRate("TRY", "USD")).thenReturn(null);

        //when
        exchangeRateService.getExchangeRate("TRY", "USD");

        //then
        Mockito.verify(ratesApiClient, Mockito.times(1)).getExchangeRate("TRY", "USD");
    }
}