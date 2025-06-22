package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.TransactionGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, String> {
    Optional<TransactionGroup> findByCodeAndDeletedAtIsNull(String code);
    Page<TransactionGroup> findAllByDeletedAtIsNull(Pageable pageable);
}