package com.plutussight.transactionservice.controller.request;

import lombok.Data;

@Data
public class CreateTransactionGroupRequest {
    private String code;
    private String name;
}