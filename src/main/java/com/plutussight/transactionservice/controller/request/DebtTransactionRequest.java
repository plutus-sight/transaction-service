package com.plutussight.transactionservice.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtTransactionRequest {
    private UUID creditCardId;
    private BigDecimal amount;
    private LocalDate date;
    private String note;
}