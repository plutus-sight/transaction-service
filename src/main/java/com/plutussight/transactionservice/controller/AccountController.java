package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.constant.ResponseCode;
import com.plutussight.transactionservice.controller.request.CreateAccountRequest;
import com.plutussight.transactionservice.controller.request.UpdateAccountRequest;
import com.plutussight.transactionservice.controller.response.AccountResponse;
import com.plutussight.transactionservice.controller.response.PagedAccountResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<ServiceResponse<PagedAccountResponse>> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedAccountResponse pagedResponse = accountService.getAllAccounts(page, size);
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, pagedResponse));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ServiceResponse<AccountResponse>> getAccountByCode(@PathVariable String code) {
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, accountService.getAccountByCode(code)));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<AccountResponse>> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, accountService.createAccount(request)));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ServiceResponse<AccountResponse>> updateAccount(
            @PathVariable String code, @RequestBody UpdateAccountRequest request) {
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, accountService.updateAccount(code, request)));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String code) {
        accountService.deleteAccount(code);
        return ResponseEntity.noContent().build();
    }
}