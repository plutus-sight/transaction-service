package com.plutussight.transactionservice.controller;


import com.plutussight.transactionservice.constant.ResponseCode;
import com.plutussight.transactionservice.controller.request.DebtTransactionRequest;
import com.plutussight.transactionservice.controller.response.DebtTransactionResponse;
import com.plutussight.transactionservice.controller.response.PagedDebtTransactionResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.service.DebtTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/debt-transactions")
@RequiredArgsConstructor
public class DebtTransactionController {

    private final DebtTransactionService debtTransactionService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<DebtTransactionResponse>> getDebtTransactionById(@PathVariable UUID id) {
        DebtTransactionResponse response = debtTransactionService.getDebtTransactionById(id);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<PagedDebtTransactionResponse>> getAllDebtTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedDebtTransactionResponse pagedResponse = debtTransactionService.getAllDebtTransactions(page, size);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, pagedResponse));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<DebtTransactionResponse>> createDebtTransaction(@RequestBody DebtTransactionRequest request) {
        DebtTransactionResponse response = debtTransactionService.createDebtTransaction(request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<DebtTransactionResponse>> updateDebtTransaction(
            @PathVariable UUID id,
            @RequestBody DebtTransactionRequest request) {
        DebtTransactionResponse response = debtTransactionService.updateDebtTransaction(id, request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteDebtTransaction(@PathVariable UUID id) {
        debtTransactionService.deleteDebtTransaction(id);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, null));
    }
}