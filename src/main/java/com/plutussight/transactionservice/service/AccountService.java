package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.CreateAccountRequest;
import com.plutussight.transactionservice.controller.request.UpdateAccountRequest;
import com.plutussight.transactionservice.controller.response.AccountResponse;
import com.plutussight.transactionservice.controller.response.PagedAccountResponse;
import com.plutussight.transactionservice.entity.Account;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.AccountMapper;
import com.plutussight.transactionservice.repository.jpa.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public PagedAccountResponse getAllAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accountPage = accountRepository.findAllByDeletedAtIsNull(pageable);
        return accountMapper.toPagedResponse(accountPage);
    }

    public AccountResponse getAccountByCode(String code) {
        return accountMapper.toResponse(accountRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found")));

    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    public AccountResponse updateAccount(String code, UpdateAccountRequest request) {
        Account account = accountRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        accountMapper.updateEntityFromRequest(request, account);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    public void updateAccountBalance(String code, BigDecimal balance) {
        Account account = accountRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setBalance(balance);
    }

    public void deleteAccount(String code) {
        accountRepository.deleteById(code);
    }
}