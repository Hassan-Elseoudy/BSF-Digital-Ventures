package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.*;
import com.bsfdv.transaction.model.Account;
import org.springframework.http.ResponseCookie;

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
    Account createOne(SignupDto accountDto) throws Exception;

    /**
     * Set balance.
     * @param accountId Account
     * @param balance balance.
     * @return Updated Account
     */
    Account setBalance(Long accountId, Long balance);

    UserInfoResponse login(LoginRequest loginRequest);

    ResponseCookie logout();



}