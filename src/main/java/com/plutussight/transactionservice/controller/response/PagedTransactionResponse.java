package com.plutussight.transactionservice.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class PagedTransactionResponse {
    private List<TransactionResponse> transactions;
    private PaginationResponse pagination;
}