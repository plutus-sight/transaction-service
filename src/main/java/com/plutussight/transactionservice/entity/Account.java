package com.plutussight.transactionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
}