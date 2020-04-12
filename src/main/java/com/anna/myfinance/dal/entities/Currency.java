package com.anna.myfinance.dal.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_name", nullable = false, length = 3, unique = true)
    private String shortName;

    @Column(name = "long_name", nullable = false, length = 50)
    private String longName;
}
