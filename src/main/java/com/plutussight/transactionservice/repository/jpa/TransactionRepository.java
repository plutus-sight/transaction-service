package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByIdAndDeletedAtIsNull(UUID id);
    Page<Transaction> findAllByDeletedAtIsNull(Pageable pageable);
}