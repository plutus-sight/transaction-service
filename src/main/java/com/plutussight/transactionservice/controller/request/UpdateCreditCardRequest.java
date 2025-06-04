package com.plutussight.transactionservice.controller.request;

import lombok.Data;

@Data
public class UpdateCreditCardRequest {
    private String name;
    private String cycleDate;
}