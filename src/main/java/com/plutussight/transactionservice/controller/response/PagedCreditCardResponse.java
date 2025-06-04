package com.plutussight.transactionservice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedCreditCardResponse {
    private List<CreditCardResponse> creditCards;
    private PaginationResponse pagination;
}