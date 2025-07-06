package com.plutussight.transactionservice.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest {
    private String groupCode;
    private String accountCode;
    private String type;
    private BigDecimal amount;
    private LocalDate date;
    private String note;
}