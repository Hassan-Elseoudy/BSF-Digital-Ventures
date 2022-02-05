package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.AddTransactionDto;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransactionServiceDefaultImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionServiceDefaultImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
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
    @Transactional
    public Transaction createOne(AddTransactionDto transactionDto) throws Exception {
        Account sender = accountService.getOne(transactionDto.getSenderId());
        Account receiver = accountService.getOne(transactionDto.getReceiverId());
        switch (transactionDto.getTransactionType()) {
            case PAY -> {
                if (sender.getBalance() >= transactionDto.getAmount()) {
                    accountService.setBalance(sender.getId(), sender.getBalance() - transactionDto.getAmount());
                    accountService.setBalance(receiver.getId(), receiver.getBalance() + transactionDto.getAmount());
                } else
                    throw new Exception("You don't have enough balance.");
            }
            case DEPOSIT -> {
                accountService.setBalance(receiver.getId(), receiver.getBalance() + transactionDto.getAmount());
            }
            case WITHDRAW -> {
                if (sender.getBalance() >= transactionDto.getAmount()) {
                    accountService.setBalance(sender.getId(), sender.getBalance() - transactionDto.getAmount());
                } else
                    throw new Exception("You don't have enough balance.");
            }
        }
        Transaction transaction = AddTransactionDto.toModel(transactionDto);
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        return transactionRepository.save(transaction);
    }

}
