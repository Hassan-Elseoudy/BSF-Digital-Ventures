package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.AddAccountDto;
import com.bsfdv.transaction.controller.dto.UpdateAccountDto;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountServiceDefaultImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceDefaultImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getOne(Long id) {
        return accountRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Account deleteOne(Long id) {
        Account account = getOne(id);
        accountRepository.deleteById(account.getId());
        return account;
    }

    @Override
    public Account updateOne(UpdateAccountDto accountDto, Long id) {
        Account account = UpdateAccountDto.toModel(accountDto, getOne(id));
        return accountRepository.save(account);
    }

    @Override
    public Account createOne(AddAccountDto accountDto) {
        Account account = AddAccountDto.toModel(accountDto);
        return accountRepository.save(account);
    }

    @Override
    public Account setBalance(Long accountId, Long balance) {
        Account account = getOne(accountId);
        account.setBalance(balance);
        return accountRepository.save(account);
    }
}
