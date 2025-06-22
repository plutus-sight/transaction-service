package com.plutussight.transactionservice.entity;

import com.plutussight.transactionservice.constant.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "group_code", nullable = false)
    private TransactionGroup group;

    @ManyToOne
    @JoinColumn(name = "account_code", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    @Column(name = "note")
    private String note;
}