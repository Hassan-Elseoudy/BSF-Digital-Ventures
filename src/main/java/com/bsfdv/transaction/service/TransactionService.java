package com.bsfdv.transaction.service;

import com.bsfdv.transaction.controller.dto.AddTransactionDto;
import com.bsfdv.transaction.model.Transaction;
import org.springframework.stereotype.Service;

@Service
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
    Transaction createOne(AddTransactionDto transactionDto) throws Exception;

}