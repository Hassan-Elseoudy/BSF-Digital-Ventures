package com.bsfdv.transaction.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "fromParty")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "toParty")
    private List<Transaction> ingoingTransactions;

}
