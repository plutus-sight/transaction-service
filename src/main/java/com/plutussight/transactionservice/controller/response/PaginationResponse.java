package com.plutussight.transactionservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationResponse {
    private int currentPage;
    private int perPage;
    private int totalPages;
    private long totalItems;
}