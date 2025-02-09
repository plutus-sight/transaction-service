package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.TransactionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, String> {
}