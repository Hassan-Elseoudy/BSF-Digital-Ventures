package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.AddAccountDto;
import com.bsfdv.transaction.controller.UpdateAccountDto;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.Transaction;

import java.util.List;

public interface AccountService {

    /**
     * get account by Id
     * @param id account id
     * @return Account
     */
    Account getOne(Long id);

    /**
     * delete account by Id
     * @param id account id
     * @return Account
     */
    Account deleteOne(Long id);

    /**
     * delete account by Id
     * @param id account id
     * @return Account
     */
    Account updateOne(UpdateAccountDto accountDto, Long id);

    /**
     * create account
     * @param accountDto create account dto
     * @return Account
     */
    Account createOne(AddAccountDto accountDto);

}