package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {
}