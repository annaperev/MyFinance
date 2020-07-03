package com.anna.myfinance.dal.entities;

import com.anna.myfinance.enums.OperationType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_item_id")
    private Item parentItem;

    @Column(name="type", nullable = false)
    private OperationType type;
}
