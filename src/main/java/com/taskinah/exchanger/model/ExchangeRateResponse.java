package com.taskinah.exchanger.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ExchangeRateResponse {
    private String base;
    private String date;
    private HashMap<String, Double> rates;
}
