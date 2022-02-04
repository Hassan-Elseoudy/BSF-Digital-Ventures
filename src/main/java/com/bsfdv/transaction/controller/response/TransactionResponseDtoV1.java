package com.bsfdv.transaction.controller.response;

import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDtoV1 {

    private Long id;
    private Long amount;
    private TransactionType transactionType;
    private Long senderId;
    private Long receiverId;


    public static TransactionResponseDtoV1 toDto(Transaction transaction) {
        return new TransactionResponseDtoV1(transaction.getId(), transaction.getAmount(), transaction.getTransactionType(), transaction.getSender().getId(), transaction.getReceiver().getId());
    }
}

