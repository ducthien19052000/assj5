package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billDetailId;
    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private double amount;
    private double price;
}
