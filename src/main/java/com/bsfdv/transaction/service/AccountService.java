package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.AddAccountDto;
import com.bsfdv.transaction.controller.dto.UpdateAccountDto;
import com.bsfdv.transaction.model.Account;

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