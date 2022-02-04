package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.AddAccountDto;
import com.bsfdv.transaction.controller.AddTransactionDto;
import com.bsfdv.transaction.controller.UpdateAccountDto;
import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.Transaction;

public interface TransactionService {

    /**
     * get transaction by Id
     * @param id transaction id
     * @return transaction
     */
    Transaction getOne(Long id);

    /**
     * delete transaction by Id
     * @param id transaction id
     * @return transaction
     */
    Transaction deleteOne(Long id);

    /**
     * create transaction
     * @param transactionDto create transaction dto
     * @return transaction
     */
    Transaction createOne(AddTransactionDto transactionDto);

}