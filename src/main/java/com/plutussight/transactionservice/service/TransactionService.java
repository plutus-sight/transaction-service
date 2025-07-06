package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.constant.TransactionType;
import com.plutussight.transactionservice.controller.request.TransactionRequest;
import com.plutussight.transactionservice.controller.response.AccountResponse;
import com.plutussight.transactionservice.controller.response.PagedTransactionResponse;
import com.plutussight.transactionservice.controller.response.TransactionResponse;
import com.plutussight.transactionservice.entity.Account;
import com.plutussight.transactionservice.entity.Transaction;
import com.plutussight.transactionservice.entity.TransactionGroup;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.TransactionMapper;
import com.plutussight.transactionservice.repository.jpa.AccountRepository;
import com.plutussight.transactionservice.repository.jpa.TransactionGroupRepository;
import com.plutussight.transactionservice.repository.jpa.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    private final TransactionGroupRepository transactionGroupRepository;

    public PagedTransactionResponse getAllTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactionPage = transactionRepository.findAllByDeletedAtIsNull(pageable);
        return transactionMapper.toPagedResponse(transactionPage);
    }

    public TransactionResponse getTransactionById(UUID id) {
        return transactionMapper.toResponse(transactionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found")));
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {
        Account account = accountRepository.findByCodeAndDeletedAtIsNull(request.getAccountCode())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        TransactionGroup transactionGroup = transactionGroupRepository.findByCodeAndDeletedAtIsNull(request.getGroupCode())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Group not found"));

        Transaction transaction = transactionMapper.toEntity(request);
        transaction.setAccount(account);
        transaction.setGroup(transactionGroup);

        BigDecimal newBalance;
        if (transaction.getType() == TransactionType.INCOME) {
            newBalance = account.getBalance().add(transaction.getAmount());
        } else {
            newBalance = account.getBalance().subtract(transaction.getAmount());
        }

        transaction.setBalance(newBalance);
        account.setBalance(newBalance);

        return transactionMapper.toResponse(transactionRepository.saveAndFlush(transaction));
    }

    public TransactionResponse updateTransaction(UUID id, TransactionRequest request) {
        Transaction transaction = transactionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transactionMapper.updateEntityFromRequest(request, transaction);
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }
}