package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.AddTransactionDto;
import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransactionServiceDefaultImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceDefaultImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction getOne(Long id) {
        return transactionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Transaction deleteOne(Long id) {
        Transaction transaction = getOne(id);
        transactionRepository.deleteById(transaction.getId());
        return transaction;
    }

    @Override
    public Transaction createOne(AddTransactionDto transactionDto) {
        Transaction transaction = AddTransactionDto.toModel(transactionDto);
        return transactionRepository.save(transaction);
    }
}
