package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.CreateTransactionDto;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.repository.TransactionRepository;
import com.bsfdv.transaction.util.error.NotEnoughBalanceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Transaction deleteOne(Long id) {
        Transaction transaction = getOne(id);
        transactionRepository.deleteById(transaction.getId());
        return transaction;
    }

    @Override
    @Transactional
    public Transaction createOne(CreateTransactionDto transactionDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetailsImpl userDetails = (AccountDetailsImpl) authentication.getPrincipal();
        Account sender = accountService.getOne(userDetails.getId());
        Transaction transaction = CreateTransactionDto.toModel(transactionDto);
        switch (transactionDto.getTransactionType()) {
            case PAY -> {
                if (sender.getBalance() >= transactionDto.getAmount()) {
                    Account receiver = accountService.getOne(transactionDto.getReceiverId());
                    accountService.setBalance(sender.getId(), sender.getBalance() - transactionDto.getAmount());
                    accountService.setBalance(receiver.getId(), receiver.getBalance() + transactionDto.getAmount());
                    transaction.setSender(sender);
                    transaction.setReceiver(receiver);
                } else
                    throw new NotEnoughBalanceException();
            }
            case DEPOSIT -> {
                accountService.setBalance(sender.getId(), sender.getBalance() + transactionDto.getAmount());
                transaction.setSender(sender);
                transaction.setReceiver(sender);
            }
            case WITHDRAW -> {
                if (sender.getBalance() >= transactionDto.getAmount()) {
                    accountService.setBalance(sender.getId(), sender.getBalance() - transactionDto.getAmount());
                    transaction.setSender(sender);
                    transaction.setReceiver(sender);
                } else
                    throw new NotEnoughBalanceException();
            }
        }


        return transactionRepository.save(transaction);
    }

}
