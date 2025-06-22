package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.constant.ResponseCode;
import com.plutussight.transactionservice.controller.request.TransactionRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.controller.response.TransactionResponse;
import com.plutussight.transactionservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ServiceResponse<PagedTransactionResponse>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedTransactionResponse pagedResponse = transactionService.getAllTransactions(page, size);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, pagedResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<TransactionResponse>> getTransactionById(@PathVariable UUID id) {
        TransactionResponse transactionResponse = transactionService.getTransactionById(id);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, transactionResponse));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse savedTransactionResponse = transactionService.createTransaction(request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, savedTransactionResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceResponse<TransactionResponse>> updateTransaction(@PathVariable UUID id, @RequestBody TransactionRequest request) {
        TransactionResponse updatedTransactionResponse = transactionService.updateTransaction(id, request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, updatedTransactionResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}