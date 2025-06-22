package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.constant.ResponseCode;
import com.plutussight.transactionservice.controller.request.CreateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.request.UpdateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionGroupResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.controller.response.TransactionGroupResponse;
import com.plutussight.transactionservice.service.TransactionGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction-groups")
@AllArgsConstructor
public class TransactionGroupController {

    private final TransactionGroupService transactionGroupService;

    @PostMapping
    public ResponseEntity<ServiceResponse<TransactionGroupResponse>> createTransactionGroup(@RequestBody CreateTransactionGroupRequest request) {
        TransactionGroupResponse response = transactionGroupService.createTransactionGroup(request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ServiceResponse<TransactionGroupResponse>> getTransactionGroupByCode(@PathVariable String code) {
        TransactionGroupResponse response = transactionGroupService.getTransactionGroupByCode(code);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ServiceResponse<TransactionGroupResponse>> updateTransactionGroup(
            @PathVariable String code,
            @RequestBody UpdateTransactionGroupRequest request) {
        TransactionGroupResponse response = transactionGroupService.updateTransactionGroup(code, request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<PagedTransactionGroupResponse>> getAllTransactionGroups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedTransactionGroupResponse pagedResponse = transactionGroupService.getAllTransactionGroups(page, size);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, pagedResponse));
    }
}