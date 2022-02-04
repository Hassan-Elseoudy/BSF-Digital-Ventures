package com.bsfdv.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne( fetch = FetchType.LAZY)
    private Account fromParty;

    @JoinColumn
    @ManyToOne( fetch = FetchType.LAZY)
    private Account toParty;

    private Long amount;

    private TransactionType transactionType;

}
