package com.plutussight.transactionservice.controller.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class TransactionResponse {
    private UUID id;
    private String groupCode;
    private String accountCode;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private OffsetDateTime date;
    private String note;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}