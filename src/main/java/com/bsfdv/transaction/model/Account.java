package com.bsfdv.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private Long balance;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> ingoingTransactions;

}
