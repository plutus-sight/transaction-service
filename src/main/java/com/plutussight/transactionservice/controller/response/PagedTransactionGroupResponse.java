package com.plutussight.transactionservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedTransactionGroupResponse {
    private List<TransactionGroupResponse> transactionGroups;
    private PaginationResponse pagination;
}