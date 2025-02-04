package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.entity.DebtTransaction;
import com.plutussight.transactionservice.repository.DebtTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DebtTransactionService {

    private final DebtTransactionRepository debtTransactionRepository;

    @Autowired
    public DebtTransactionService(DebtTransactionRepository debtTransactionRepository) {
        this.debtTransactionRepository = debtTransactionRepository;
    }

    public List<DebtTransaction> getAllDebtTransactions() {
        return debtTransactionRepository.findAll();
    }

    public Optional<DebtTransaction> getDebtTransactionById(UUID id) {
        return debtTransactionRepository.findById(id);
    }

    public DebtTransaction saveDebtTransaction(DebtTransaction debtTransaction) {
        return debtTransactionRepository.save(debtTransaction);
    }

    public void deleteDebtTransaction(UUID id) {
        debtTransactionRepository.deleteById(id);
    }
}