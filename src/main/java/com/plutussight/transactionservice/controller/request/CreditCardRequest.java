package com.plutussight.transactionservice.controller.request;

import lombok.Data;

@Data
public class CreditCardRequest {
    private String name;
    private String cycleDate;
}