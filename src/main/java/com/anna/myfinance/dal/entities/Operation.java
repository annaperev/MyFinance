package com.anna.myfinance.dal.entities;

import com.anna.myfinance.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @Column(name="type", nullable = false)
    private OperationType type;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id",nullable = false)
    private Item item;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id",nullable = false)
    private Account account;

    @Column(name="amount",columnDefinition = "DECIMAL(10,2)",nullable = false)
    private BigDecimal amount;

    @Column(name="comment",columnDefinition = "TEXT")
    private String comment;
}
