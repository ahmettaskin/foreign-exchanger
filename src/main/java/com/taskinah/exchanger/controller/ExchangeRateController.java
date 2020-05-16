package com.taskinah.exchanger.controller;


import com.taskinah.exchanger.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/exchange")
@Slf4j
@AllArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String symbols, @RequestParam String base) {
        log.info("Get exchange rate request is received. symbols: {},  base: {}", symbols, base);
        var exchangeRate = exchangeRateService.getExchangeRate(symbols, base);
        return ResponseEntity.ok(exchangeRate);
    }
}
