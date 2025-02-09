package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.entity.TransactionGroup;
import com.plutussight.transactionservice.repository.jpa.TransactionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionGroupService {

    private final TransactionGroupRepository transactionGroupRepository;

    @Autowired
    public TransactionGroupService(TransactionGroupRepository transactionGroupRepository) {
        this.transactionGroupRepository = transactionGroupRepository;
    }

    public List<TransactionGroup> getAllTransactionGroups() {
        return transactionGroupRepository.findAll();
    }

    public Optional<TransactionGroup> getTransactionGroupByCode(String code) {
        return transactionGroupRepository.findById(code);
    }

    public TransactionGroup saveTransactionGroup(TransactionGroup transactionGroup) {
        return transactionGroupRepository.save(transactionGroup);
    }

    public void deleteTransactionGroup(String code) {
        transactionGroupRepository.deleteById(code);
    }
}