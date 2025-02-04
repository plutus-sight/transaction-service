package com.plutussight.transactionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "transaction_group")
public class TransactionGroup extends BaseEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;
}