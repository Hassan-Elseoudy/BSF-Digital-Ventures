package com.bsfdv.transaction.controller.dto;

import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.model.TransactionType;
import com.bsfdv.transaction.util.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionDto {

    @ValueOfEnum(regexp = "WITHDRAW|DEPOSIT|PAY")
    private TransactionType transactionType;

    @Positive
    private Long amount;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    public static Transaction toModel(AddTransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        return transaction;
    }

}
