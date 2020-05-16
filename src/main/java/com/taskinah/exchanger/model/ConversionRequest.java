package com.taskinah.exchanger.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionRequest {
    private String sourceCurrency;
    private String targetCurrency;
    private Double amount;

    @Override
    public String toString() {
        return "ConversionRequest{" +
                "sourceCurrency='" + sourceCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
