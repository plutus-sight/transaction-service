package com.plutussight.transactionservice.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UpdateTransactionRequest {
    private String groupCode;
    private String accountCode;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private OffsetDateTime date;
    private String note;
}