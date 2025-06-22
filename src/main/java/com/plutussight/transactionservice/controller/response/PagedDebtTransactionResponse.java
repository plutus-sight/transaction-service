package com.plutussight.transactionservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedDebtTransactionResponse {
    private List<DebtTransactionResponse> debtTransactions;
    private PaginationResponse pagination;
}