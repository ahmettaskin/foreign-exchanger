package com.taskinah.exchanger.controller;

import com.taskinah.exchanger.entity.Transaction;
import com.taskinah.exchanger.model.ConversionRequest;
import com.taskinah.exchanger.service.ConversionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/conversion")
@Slf4j
@AllArgsConstructor
public class ConversionController {

    private final ConversionService conversionCurrencyService;

    @PostMapping("")
    public ResponseEntity<Transaction> convert(@RequestBody ConversionRequest request) {
        log.info("Conversion request is received. Request: {}", request);
        return ResponseEntity.ok(conversionCurrencyService.convert(request));
    }

    @GetMapping("")
    public ResponseEntity<Page<Transaction>> list(Pageable pageable, @RequestParam(required = false) String transactionId,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date fromDate,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "MMddyyyy") Date toDate) {

        log.info("Get conversions request is received. TransactionId: {},  fromDate: {}, toDate: {}", transactionId, fromDate, toDate);
        if (transactionId != null) {
            return ResponseEntity.ok(conversionCurrencyService.getTransactionsByTransactionId(pageable, transactionId));
        } else if (fromDate != null && toDate != null) {
            return ResponseEntity.ok(conversionCurrencyService.getTransactionsByDate(pageable, fromDate, toDate));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

