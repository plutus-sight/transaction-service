package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.DebtTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebtTransactionRepository extends JpaRepository<DebtTransaction, UUID> {
}