package com.github.shushkevich.receiptprocessor;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String retailer;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private LocalTime purchaseTime;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptItem> items;
}
