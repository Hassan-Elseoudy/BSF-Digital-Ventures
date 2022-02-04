package com.bsfdv.transaction.controller.response;

import com.bsfdv.transaction.model.Account;
import com.bsfdv.transaction.model.Transaction;
import com.bsfdv.transaction.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDtoV1 {

    private Long id;
    private String name;
    private String country;
    private Long balance;

    public static AccountResponseDtoV1 toDto(Account account) {
        return new AccountResponseDtoV1(account.getId(), account.getName(), account.getCountry(), account.getBalance());
    }
}

