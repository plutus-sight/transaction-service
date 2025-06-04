package com.plutussight.transactionservice.controller.request;

import lombok.Data;

@Data
public class CreateCreditCardRequest {
    private String name;
    private String cycleDate;
}