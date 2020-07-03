package com.anna.myfinance.dal.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tranfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_from_id",nullable = false)
    private Account accountFrom;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_to_id",nullable = false)
    private Account accountTo;

    @Column(name="amount_from",columnDefinition = "DECIMAL(10,2)",nullable = false)
    private BigDecimal amountFrom;

    @Column(name="amount_to",columnDefinition = "DECIMAL(10,2)",nullable = false)
    private BigDecimal amountTo;

    @Column(name="comment",columnDefinition = "TEXT")
    private String comment;
}
