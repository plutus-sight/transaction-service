package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.CreateTransactionRequest;
import com.plutussight.transactionservice.controller.request.UpdateTransactionRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionResponse;
import com.plutussight.transactionservice.controller.response.TransactionResponse;
import com.plutussight.transactionservice.entity.Transaction;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.TransactionMapper;
import com.plutussight.transactionservice.repository.jpa.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public PagedTransactionResponse getAllTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);
        return transactionMapper.toPagedResponse(transactionPage);
    }

    public TransactionResponse getTransactionById(UUID id) {
        return transactionMapper.toResponse(transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found")));
    }

    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        Transaction transaction = transactionMapper.toEntity(request);
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    public TransactionResponse updateTransaction(UUID id, UpdateTransactionRequest request) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transactionMapper.updateEntityFromRequest(request, transaction);
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }
}