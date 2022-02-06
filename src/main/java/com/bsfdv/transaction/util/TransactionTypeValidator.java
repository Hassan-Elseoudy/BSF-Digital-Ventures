package com.bsfdv.transaction.util;

import com.bsfdv.transaction.controller.dto.CreateTransactionDto;
import com.bsfdv.transaction.model.TransactionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TransactionTypeValidator implements ConstraintValidator<TransactionValid, CreateTransactionDto> {

    @Override
    public void initialize(TransactionValid constraint) {
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(CreateTransactionDto transactionDto, ConstraintValidatorContext context) {
        if (transactionDto.getTransactionType() == TransactionType.PAY) {
            return transactionDto.getReceiverId() != null;
        }
        return true;
    }
}