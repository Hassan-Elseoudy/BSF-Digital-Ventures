package com.bsfdv.transaction.controller.dto;

import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.model.TransactionType;
import com.bsfdv.transaction.util.TransactionValid;
import com.bsfdv.transaction.util.ValueOfEnum;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TransactionValid
public class CreateTransactionDto {

    @ValueOfEnum(regexp = "WITHDRAW|DEPOSIT|PAY")
    private TransactionType transactionType;

    @Positive
    private Long amount;

    private Long receiverId;

    public static Transaction toModel(CreateTransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        return transaction;
    }

}
