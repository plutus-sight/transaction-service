package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.CreditCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByIdAndDeletedAtIsNull(UUID id);
    Page<CreditCard> findAllByDeletedAtIsNull(Pageable pageable);
}