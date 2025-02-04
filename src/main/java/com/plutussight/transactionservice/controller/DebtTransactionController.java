package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.entity.DebtTransaction;
import com.plutussight.transactionservice.service.DebtTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/debt-transactions")
public class DebtTransactionController {

    private final DebtTransactionService debtTransactionService;

    @Autowired
    public DebtTransactionController(DebtTransactionService debtTransactionService) {
        this.debtTransactionService = debtTransactionService;
    }

    @GetMapping
    public List<DebtTransaction> getAllDebtTransactions() {
        return debtTransactionService.getAllDebtTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtTransaction> getDebtTransactionById(@PathVariable UUID id) {
        return debtTransactionService.getDebtTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DebtTransaction createDebtTransaction(@RequestBody DebtTransaction debtTransaction) {
        return debtTransactionService.saveDebtTransaction(debtTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebtTransaction> updateDebtTransaction(@PathVariable UUID id, @RequestBody DebtTransaction debtTransaction) {
        if (!id.equals(debtTransaction.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(debtTransactionService.saveDebtTransaction(debtTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebtTransaction(@PathVariable UUID id) {
        debtTransactionService.deleteDebtTransaction(id);
        return ResponseEntity.noContent().build();
    }
}