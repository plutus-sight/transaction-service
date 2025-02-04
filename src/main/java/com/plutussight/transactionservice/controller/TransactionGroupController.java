package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.entity.TransactionGroup;
import com.plutussight.transactionservice.service.TransactionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-groups")
public class TransactionGroupController {

    private final TransactionGroupService transactionGroupService;

    @Autowired
    public TransactionGroupController(TransactionGroupService transactionGroupService) {
        this.transactionGroupService = transactionGroupService;
    }

    @GetMapping
    public List<TransactionGroup> getAllTransactionGroups() {
        return transactionGroupService.getAllTransactionGroups();
    }

    @GetMapping("/{code}")
    public ResponseEntity<TransactionGroup> getTransactionGroupByCode(@PathVariable String code) {
        return transactionGroupService.getTransactionGroupByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TransactionGroup createTransactionGroup(@RequestBody TransactionGroup transactionGroup) {
        return transactionGroupService.saveTransactionGroup(transactionGroup);
    }

    @PutMapping("/{code}")
    public ResponseEntity<TransactionGroup> updateTransactionGroup(@PathVariable String code, @RequestBody TransactionGroup transactionGroup) {
        if (!code.equals(transactionGroup.getCode())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(transactionGroupService.saveTransactionGroup(transactionGroup));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteTransactionGroup(@PathVariable String code) {
        transactionGroupService.deleteTransactionGroup(code);
        return ResponseEntity.noContent().build();
    }
}