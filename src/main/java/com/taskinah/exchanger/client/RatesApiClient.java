package com.taskinah.exchanger.client;

import com.taskinah.exchanger.model.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RatesApiClient {

    private final RestTemplate restTemplate;

    private final String ratesApiClientUrl;

    @Autowired
    public RatesApiClient(RestTemplate restTemplate,
                          @Value("${api.client.exchangeRateProviderUrl}") String ratesApiClientUrl) {
        this.restTemplate = restTemplate;
        this.ratesApiClientUrl = ratesApiClientUrl;
    }

    public Double getExchangeRate(String symbols, String base) {
        var apiUrl = String.format("%s/latest?base=%s&symbols=%s", ratesApiClientUrl, base, symbols);

        var responseEntity = restTemplate.getForEntity(apiUrl, ExchangeRateResponse.class);

        if (responseEntity.hasBody() && responseEntity.getBody() != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody().getRates().get(symbols);
        }
        return null;
    }
}
