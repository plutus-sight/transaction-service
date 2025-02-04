package com.plutussight.transactionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "debt_transaction")
public class DebtTransaction extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "note")
    private String note;
}