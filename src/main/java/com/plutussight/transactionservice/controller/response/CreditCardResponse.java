package com.plutussight.transactionservice.controller.response;

import lombok.Data;

import java.time.Instant;

@Data
public class CreditCardResponse {
    private String id;
    private String name;
    private String cycleDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}