package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.entity.Account;
import com.plutussight.transactionservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Account> getAccountByCode(@PathVariable String code) {
        return accountService.getAccountByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Account> updateAccount(@PathVariable String code, @RequestBody Account account) {
        if (!code.equals(account.getCode())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountService.saveAccount(account));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String code) {
        accountService.deleteAccount(code);
        return ResponseEntity.noContent().build();
    }
}