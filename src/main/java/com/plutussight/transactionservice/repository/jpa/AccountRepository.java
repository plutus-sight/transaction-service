package com.plutussight.transactionservice.repository.jpa;

import com.plutussight.transactionservice.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByCodeAndDeletedAtIsNull(String code);
    Page<Account> findAllByDeletedAtIsNull(Pageable pageable);
}