package com.anna.myfinance.dal.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="currency_id",nullable = false)
    private Currency currency;

    @Column(name="amount",columnDefinition = "DECIMAL(10,2)",nullable = false)
    private BigDecimal amount;

    @Column(name="comment",columnDefinition = "TEXT")
    private String comment;
}
