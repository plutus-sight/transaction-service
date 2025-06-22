package com.plutussight.transactionservice.controller.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TransactionGroupResponse {
    private String code;
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}