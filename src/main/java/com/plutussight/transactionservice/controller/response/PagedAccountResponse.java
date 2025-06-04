package com.plutussight.transactionservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedAccountResponse {
    private List<AccountResponse> accounts;
    private PaginationResponse pagination;
}